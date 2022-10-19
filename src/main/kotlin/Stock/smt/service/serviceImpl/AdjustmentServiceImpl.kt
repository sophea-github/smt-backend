package Stock.smt.service.serviceImpl

import Stock.smt.model.Adjustment
import Stock.smt.model.AdjustmentDetail
import Stock.smt.model.Custom.DTO.AdDTO
import Stock.smt.model.Custom.DTO.AdjustmentDTO
import Stock.smt.model.Custom.DTO.AdjustmentRequest
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.repository.*
import Stock.smt.service.AdjustmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Service
class AdjustmentServiceImpl: AdjustmentService {
    @Autowired lateinit var adjustmentTypeRepository: AdjustmentTypeRepository
    @Autowired lateinit var adjustmentRepository: AdjustmentRepository
    @Autowired lateinit var adjustmentDetailRepository: AdjustmentDetailRepository
    @Autowired lateinit var responseObjectMap: ResponseObjectMap
    @Autowired lateinit var productRepository: ProductRepository
    @Autowired lateinit var employeeRepository: EmployeeRepository
    @Autowired lateinit var documentarySettingRepository: DocumentarySettingRepository
    override fun findAll(): List<Adjustment>? {
        return adjustmentRepository.findAll()
    }
    override fun findAdjustment(id: Int): AdjustmentDTO? {
        return adjustmentRepository.findAdjustment(id)
    }
    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
    override fun saveAll(t: Adjustment): Adjustment? {
        TODO("Not yet implemented")
    }
    override fun updateObj(id: Int, t: Adjustment): Adjustment? {
        TODO("Not yet implemented")
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<Adjustment> {
        TODO("Not yet implemented")
    }
    override fun findCountAdjustment(): List<AdDTO> {
        return adjustmentRepository.findCountAdjustment()
    }
    override fun addAdjustment(adjustmentId: Int, req: AdjustmentRequest): MutableMap<String,Any>? {
        val adjType= adjustmentTypeRepository.findByIdAndStatusIsTrue(adjustmentId)
        val currentDate = LocalDateTime.now()
        val formatter = SimpleDateFormat("yyyyMMdd")
        val adCode = documentarySettingRepository.findByIdAndStatusIsTrue(1)
        adCode.lastcode +=1
        documentarySettingRepository.save(adCode)
        synchronized(this){
            val adjustment = adjustmentRepository.save(
                Adjustment(
                    id = req.id,
                    code = adCode.prefix.plus(formatter.format(Date()).toString().plus(adCode.lastcode)),
                    adjustmentDate = req.adjustmentDate,
                    adjustmentType = adjType,
                    description = req.description,
                    create_by = req.create_by,
                    create_date = currentDate,
                    update_by = req.update_by,
                    update_date = currentDate
                )
            )
            req.adjustmentDetail!!.map {
                val pro = productRepository.findByIdAndStatusIsTrue(it.product!!.id)
                val emp = employeeRepository.findByIdAndStatusTrue(it.employee!!.id)
                adjustmentDetailRepository.save(
                    AdjustmentDetail(
                        id = it.id,
                        product = pro,
                        adjustment = adjustment,
                        itemVariantUom = pro!!.itemVariantUom,
                        employee= emp,
                        qty = it.qty * pro.itemVariantUom!!.conversion_factor,
                        reason = it.reason,
                        create_by = it.create_by,
                        create_date = currentDate,
                        update_date = currentDate,
                        update_by = it.update_by
                    )
                )

                if(adjType.id == 7 ){
                        pro.qty += it.qty
                        pro.amt = pro.qty * pro.itemVariantUom!!.conversion_factor
                }else if(adjType.id == 6){
                    if(pro.qty >= it.qty){
                        pro.qty -= it.qty
                        pro.amt = pro.qty * pro.itemVariantUom!!.conversion_factor
                    }else{
                        return responseObjectMap.responseOBJ(501,"Not Available !!")
                    }
                }

                if(pro.amt>0 && pro.qty > 1){
                    pro.active = "Available"
                    pro.stock_alert = "Normal Stock"
                }else if(pro.qty ==1){
                    pro.stock_alert = "Low Stock"
                    pro.active = "Available"
                }else{
                    pro.active = "UnAvailable"
                }
                productRepository.save(pro)
            }
        }
       return responseObjectMap.responseObj("Success !!")
    }
    override fun updateAdjustment(adjustmentId: Int,id: Int, req: AdjustmentRequest): MutableMap<String, Any>? {
        val adjustmentType = adjustmentTypeRepository.findByIdAndStatusIsTrue(adjustmentId)
        val currentDate = LocalDateTime.now()
        synchronized(this){
            val adjustment = adjustmentRepository.findByIdAndStatusIsTrue(id)
            adjustment!!.adjustmentDate = req.adjustmentDate
            adjustment!!.adjustmentType = adjustmentType
            adjustment.update_by = req.update_by
            adjustment.update_date = currentDate
            adjustment.description = req.description
            adjustmentRepository.save(adjustment)
            req.adjustmentDetail!!.map {
                val adjustmentDetail = adjustmentDetailRepository.findByIdAndStatusIsTrue(it.id)
                val emp = employeeRepository.findByIdAndStatusTrue(it.employee!!.id)
                adjustmentDetail!!.employee = emp
                adjustmentDetail!!.reason = it.reason
                adjustmentDetailRepository.save(adjustmentDetail)
                println(adjustmentDetail.employee!!.email)
            }
        }
        return responseObjectMap.responseObj("Update Success")
    }
    override fun deleteAdjustment(id: Int): MutableMap<String, Any>? {
        val adjustment = adjustmentRepository.findByIdAndStatusIsTrue(id)
        val adjId = adjustment!!.id
        adjustmentDetailRepository.deleteAdjById(adjId)
        adjustmentRepository.deleteById(adjId)
        return responseObjectMap.responseObj("Success !!")
    }
}