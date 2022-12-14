package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.dto.PurchaseOrderDTO
import Stock.smt.model.custom.dto.PurchaseOrderRequest
import Stock.smt.model.custom.dto.PoDTO
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.PurchaseOrder
import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.repository.*
import Stock.smt.service.ProductService
import Stock.smt.service.PurchaseOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PurchaseOrderServiceImpl : PurchaseOrderService {
    @Autowired
    lateinit var poRepository: PurchaseOrderRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    @Autowired
    lateinit var purchaseOrderDetailRepository: PurchaseOrderDetailRepository

    @Autowired
    lateinit var changeRateRepository: ChangeRateRepository

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    override fun findPo(id: Int): PoDTO? {
        return poRepository.findPurchase(id)
    }

    override fun findAll(): List<PurchaseOrder>? {
        return poRepository.findAll()
    }

    override fun saveAll(t: PurchaseOrder): PurchaseOrder? {
        TODO()
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<PurchaseOrder> {
        TODO("Not yet implemented")
    }

    override fun updateObj(id: Int, t: PurchaseOrder): PurchaseOrder? {
        TODO("Not yet implemented")
    }

    override fun findAllPO(): List<PurchaseOrderDTO> {
        return poRepository.findCountPo()
    }

    override fun delete(id: Int) {
        TODO()
    }

    override fun addPO(cr_id: Int, emp_id: Int, supId: Int, req: PurchaseOrderRequest): MutableMap<String, Any>? {
        val emp = employeeRepository.findByIdAndStatusTrue(emp_id)
        val cr = changeRateRepository.findByIdAndStatusIsTrue(cr_id)
        val sup = supplierRepository.findByIdAndStatusTrue(supId)
        val currentDate = LocalDateTime.now()
        synchronized(this) {
            val a = poRepository.existsByCodeAndSupplier(req.code!!, sup!!)
            if (!a) {
                try {
                    val po = poRepository.save(
                        PurchaseOrder(
                            id = 0,
                            employee = emp,
                            code = req.code!!,
                            supplier = sup,
                            order_date = req.order_date,
                            create_by = req.create_by,
                            create_date = currentDate,
                            description = req.description,
                            changeRate = cr,
                        )
                    )
                    req.purchaseOrderDetail!!.map {
                        val pro = productService.findProductById(it.product!!.id)
                        purchaseOrderDetailRepository.save(
                            PurchaseOrderDetail(
                                id = 0,
                                product = pro,
                                itemVariantUom = pro?.itemVariantUom!!,
                                qty = it.qty.toInt() * pro.itemVariantUom!!.conversion_factor,
                                purchaseOrder = po,
                                price = it.price,
                                create_by = req.create_by,
                                create_date = currentDate
                            )
                        )
                    }
                } catch (e: Exception) {
                }
            } else {
                return responseObjectMap.responseOBJ(501, "Existing !!")
            }
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }

    override fun updatePO(
        emp_id: Int,
        currency_id: Int,
        supId: Int,
        po_id: Int,
        req: PurchaseOrderRequest,
    ): MutableMap<String, Any>? {
        val po = poRepository.findByIdAndStatusIsTrue(po_id)
        val emp = employeeRepository.findByIdAndStatusTrue(emp_id)
        val cr = changeRateRepository.findByIdAndStatusIsTrue(currency_id)
        val sup = supplierRepository.findByIdAndStatusTrue(supId)
        val currentDate = LocalDateTime.now()
        synchronized(this) {
            val check = poRepository.findByCodeAndSupplier(req.code!!, sup!!)
            if (po?.id == check?.id || check?.id == null) {
                try {
                    po?.employee = emp
                    po?.code = req.code!!
                    po?.supplier = sup
                    po?.order_date = req.order_date
                    po?.create_by = req.create_by
                    po?.description = req.description
                    po?.changeRate = cr
                    po?.create_date = currentDate
                    val purchase = poRepository.save(po!!)
                    val oldPo = req.purchaseOrderDetail?.filter { it.poid != null }
                    oldPo!!.map {
                        val pd = purchaseOrderDetailRepository.findByIdAndStatusIsTrue(it.poid!!)
                        val pro = productRepository.findByIdAndStatusIsTrue(it.product!!.id)
                        pd?.product = pro
                        pd?.itemVariantUom = pro?.itemVariantUom!!
                        pd?.qty = it.qty.toInt() * pro.itemVariantUom!!.conversion_factor
                        pd?.price = it.price
                        pd?.purchaseOrder = purchase
                        pd?.create_by = it.create_by
                        pd?.create_date = currentDate
                        purchaseOrderDetailRepository.save(pd!!)
                    }
                } catch (e: Exception) {
                }
            } else {
                return responseObjectMap.responseOBJ(501, "Existing !!")
            }
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }

    override fun deletePO(id: Int): MutableMap<String, Any>? {
        val po = poRepository.findByIdAndStatusIsTrue(id)
        val pcId = po!!.id
        purchaseOrderDetailRepository.deletePoDByPoId(pcId)
        poRepository.deleteById(pcId)
        return responseObjectMap.responseOBJ(200, "delete success !!")
    }
}