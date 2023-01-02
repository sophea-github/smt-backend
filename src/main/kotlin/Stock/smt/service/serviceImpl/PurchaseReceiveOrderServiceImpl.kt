package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.dto.PorDTO
import Stock.smt.model.custom.dto.PurchaseReceiveDTO
import Stock.smt.model.custom.dto.PurchaseReceiveOrderRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.model.PurchaseReceive
import Stock.smt.model.PurchaseReceiveDetail
import Stock.smt.repository.*
import Stock.smt.service.PurchaseReceiveOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime
@Service
class PurchaseReceiveOrderServiceImpl: PurchaseReceiveOrderService {
    @Autowired
    lateinit var purchaseReceiveOrderRepository: PurchaseReceiveOrderRepository
    @Autowired
    lateinit var employeeRepository: EmployeeRepository
    @Autowired
    lateinit var changeRateRepository: ChangeRateRepository
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @Autowired
    lateinit var purchaseReceiveOrderDetailRepository: PurchaseReceiveOrderDetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var purchaseOrderDetailRepository: PurchaseOrderDetailRepository
    @Autowired
    lateinit var purchaseOrderRepository: PurchaseOrderRepository

    override fun findAll(): List<PurchaseReceive>? {
        TODO("Not yet implemented")
    }
    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
    override fun saveAll(t: PurchaseReceive): PurchaseReceive? {
        TODO("Not yet implemented")
    }
    override fun updateObj(id: Int, t: PurchaseReceive): PurchaseReceive? {
        TODO("Not yet implemented")
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<PurchaseReceive> {
        TODO("Not yet implemented")
    }
    override fun findProOrderDetail(proId: Int, code: String,suppId: Int): PurchaseOrderDetail? {
        return purchaseReceiveOrderDetailRepository.findProductOrder(proId,code, suppId)
    }
    override fun findAllPor(): List<PurchaseReceiveDTO> {
        return purchaseReceiveOrderRepository.findCountPor()
    }
    override fun findPorDetail(id: Int): PorDTO {
       return purchaseReceiveOrderRepository.findPoReceive(id)
    }
    override fun addPurchaseReceive(empId: Int,req: PurchaseReceiveOrderRequest): MutableMap<String, Any> {
        val emp = employeeRepository.findByIdAndStatusTrue(empId)
        val currentDate = LocalDateTime.now()
        val purchaseOrder = purchaseOrderRepository.findByIdAndStatusIsTrue(req.purchase_order!!.id)
        synchronized(this){
            if(purchaseOrder != null){
                val por = purchaseReceiveOrderRepository.save(
                    PurchaseReceive(
                        id = req.id,
                        code = purchaseOrder.code,
                        supplier = purchaseOrder.supplier,
                        employee = emp,
                        create_date = currentDate,
                        changeRate = purchaseOrder.changeRate,
                        create_by = req.create_by!!,
                        receive_date = req.receive_date,
                        description = req.description!!,
                        purchase_order = purchaseOrder
                    )
                )
                req.purchaseReceiveDetail!!.map{
                    val pro = productRepository.findByIdAndStatusIsTrue(it.product!!.id)
                    val pod = purchaseOrderDetailRepository.findPod(pro!!.id, req.purchase_order!!.id)
                    if(pod != null ){
                        purchaseReceiveOrderDetailRepository.save(
                            PurchaseReceiveDetail(
                                id = it.id,
                                qty = it.qty * pro.itemVariantUom!!.conversion_factor,
                                product = pro,
                                description = it.description!!,
                                create_date = currentDate,
                                purchaseReceive = por,
                                purchaseOrderDetail = pod
                            )
                        )
                        pro.qty = pro.qty + it.qty
                        pro.price = pod.price!!.toFloat()
                        pro.amt = pro.qty * pro.itemVariantUom!!.conversion_factor
                        if(pro.amt>0){
                            pro.active = "Available"
                        }else{
                            pro.active = "UnAvailable"
                        }
                        productRepository.save(pro)
                    }else{
                        return responseObjectMap.responseOBJ(500,"Item not be orders !!")
                    }
                }
            }else{
                return responseObjectMap.responseOBJ(500,"Item not be order !!")
            }
        }
        return responseObjectMap.responseObj("Success !!")
    }
    override fun updatePurchaseReceive(empId: Int, crId: Int, porId: Int , req: PurchaseReceiveOrderRequest): MutableMap<String, Any> {
        val emp = employeeRepository.findByIdAndStatusTrue(empId)
        val currency = changeRateRepository.findByIdAndStatusIsTrue(crId)
        val currentDate = LocalDateTime.now()
        val purchaseOrder = purchaseOrderRepository.findByIdAndStatusIsTrue(req.purchase_order!!.id)
        val poReceive = purchaseReceiveOrderRepository.findByIdAndStatusIsTrue(porId)
        synchronized(this){
            poReceive?.code = purchaseOrder!!.code
            poReceive?.employee = emp
            poReceive?.supplier = purchaseOrder.supplier
            poReceive?.changeRate = currency
            poReceive?.create_date = currentDate
            poReceive?.create_by = req.create_by!!
            poReceive?.description = req.description!!
            poReceive?.receive_date = req.receive_date
            poReceive?.purchase_order = purchaseOrder
            purchaseReceiveOrderRepository.save(poReceive!!)
            req.purchaseReceiveDetail!!.map {
                val poRd = purchaseReceiveOrderDetailRepository.findPord(it.product!!.id,poReceive.id)
                val prd = purchaseReceiveOrderDetailRepository.findByIdAndStatusIsTrue(it.id)
                val pro = productRepository.findByIdAndStatusIsTrue(it.product!!.id)
                if(poRd != null) {
                    prd?.qty = it.qty
                    prd?.product = pro
                    prd?.description = it.description!!
                    prd?.create_by = it.create_by
                    prd?.create_date = currentDate
                    purchaseReceiveOrderDetailRepository.save(prd!!)
                }else {
                    return responseObjectMap.responseOBJ(500,"Item not be receive !!")
                }
            }
        }
        return responseObjectMap.responseObj("Success !!")
    }
    override fun deletePor(id: Int): MutableMap<String, Any> {
        val por =  purchaseReceiveOrderRepository.findByIdAndStatusIsTrue(id)
        val poId = por!!.id
        purchaseReceiveOrderDetailRepository.deletePorDByPoId(poId)
        purchaseReceiveOrderRepository.deleteById(poId)
        return responseObjectMap.responseOBJ(200,"Success !!")
    }
}