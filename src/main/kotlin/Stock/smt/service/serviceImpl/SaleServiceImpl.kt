package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.dto.SaleRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.Sale
import Stock.smt.model.SaleDetail
import Stock.smt.repository.*
import Stock.smt.service.SaleService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Service
class SaleServiceImpl constructor(
    private val customerRepository: CustomerRepository,
    private val employeeRepository: EmployeeRepository,
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository,
    private val saleDetailRepository: SaleDetailRepository,
    private val documentarySettingRepository: DocumentarySettingRepository,
    private val responseObjectMap: ResponseObjectMap
): SaleService {
    override fun findAll(): List<Sale>? {
        TODO("Not yet implemented")
    }
    override fun saveAll(t: Sale): Sale? {
        TODO("Not yet implemented")
    }
    override fun updateObj(id: Int, t: Sale): Sale? {
        TODO("Not yet implemented")
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<Sale> {
        TODO("Not yet implemented")
    }
    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
    override fun getSale(): MutableList<Sale> {
      return saleRepository.findAll()
    }
    override fun updateSale(empId: Int, cusId: Int, id: Int, t: SaleRequest): MutableMap<String, Any> {
        val emp = employeeRepository.findByIdAndStatusTrue(empId)
        val cus = customerRepository.findByIdAndStatusIsTrue(cusId)
        val currentDate = LocalDateTime.now()
        val saleUpdate =  saleRepository.findByIdAndStatusIsTrue(id)
        saleUpdate!!.customer =  cus
        saleUpdate.employee = emp
        saleUpdate.saleDate = t.saleDate
        saleUpdate.description = t.description
        saleUpdate.update_by = t.update_by
        saleUpdate.update_date = currentDate
       return responseObjectMap.responseObj(saleRepository.save(saleUpdate))
    }
    override fun newSale(empId: Int, cusId: Int, t: SaleRequest): MutableMap<String,Any> {
        val emp = employeeRepository.findByIdAndStatusTrue(empId)
        val cus = customerRepository.findByIdAndStatusIsTrue(cusId)
        val currentDate = LocalDateTime.now()
        val formatter = SimpleDateFormat("yyyyMMdd")
        val adCode = documentarySettingRepository.findByIdAndStatusIsTrue(2)
        adCode.lastcode +=1
        documentarySettingRepository.save(adCode)
        synchronized(this){
            val sl = saleRepository.save(
                Sale(
                    id = t.id!!,
                    code = adCode.prefix.plus(formatter.format(Date()).toString().plus(adCode.lastcode)),
                    customer = cus,
                    employee = emp,
                    saleDate = t.saleDate,
                    description = t.description,
                    create_by = t.create_by,
                    create_date = currentDate,
                    update_by = t.update_by,
                    update_date = currentDate
                )
            )
            t.saleDetail?.forEach{
                val pro =  productRepository.findByIdAndStatusIsTrue(it.product!!.id)
                saleDetailRepository.save(
                    SaleDetail(
                        id = it.id,
                        product = pro,
                        itemVariantUom = pro?.itemVariantUom,
                        qty = it.qty,
                        unit_price = it.unit_price,
                        create_by = it.create_by,
                        create_date = currentDate,
                        sale = sl
                    )
                )

                if(pro!!.qty >= it.qty){
                    pro.qty -= it.qty
                    pro.amt = pro.qty * pro.itemVariantUom!!.conversion_factor
                }else{
                    return responseObjectMap.responseOBJ(501,"Not Available !!")
                }

                if(pro.amt> 0 && pro.qty > 1){
                    pro.active = "Available"
                    pro.stock_alert = "Normal Stock"
                }else if(pro.qty == 1){
                    pro.stock_alert = "Low Stock"
                    pro.active = "Available"
                }else{
                    pro.active = "UnAvailable"
                }
                productRepository.save(pro)
            }
        }
        return responseObjectMap.responseObj("Sale Success")
    }
    override fun deleteSale(id: Int): MutableMap<String, Any> {
        val sale = saleRepository.findByIdAndStatusIsTrue(id)
        val sldId = sale!!.id
        saleRepository.deleteSaleById(sldId)
        saleRepository.deleteById(sldId)
        return responseObjectMap.responseObj("Success !!")
    }
}