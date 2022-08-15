package Stock.smt.service.serviceImpl

import Stock.smt.model.Custom.DTO.Purchase_OrderRequest
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Employee
import Stock.smt.model.Purchase_Order
import Stock.smt.model.Purchase_Order_Detail
import Stock.smt.repository.*
import Stock.smt.service.Purchase_OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class Purchase_OrderServiceImpl: Purchase_OrderService {
    @Autowired
    lateinit var po_repository: Purchase_OrderRepository
    @Autowired
    lateinit var employeeRepository: EmployeeRepository
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @Autowired
    lateinit var purchaseOrderDetailrepository: Purchase_Order_DetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var itemVeriantUomRepository: ItemVeriantUomRepository

    override fun findAll(): List<Purchase_Order>? = po_repository.findAll()

    override fun saveAll(t: Purchase_Order): Purchase_Order?{
        TODO()
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<Purchase_Order> {
        TODO("Not yet implemented")
    }
    override fun updateObj(id: Int, t: Purchase_Order): Purchase_Order? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        purchaseOrderDetailrepository.deleteById(id)
        po_repository.deleteById(id)

    }

    override fun addPO( empId: Int,pro_id: Int,it_id: Int, req: Purchase_OrderRequest): MutableMap<String, Any>? {
        val emp = employeeRepository.findByIdAndStatusTrue(empId)
        var pro = productRepository.findByIdAndStatusIsTrue(pro_id)
        var itm = itemVeriantUomRepository.findByIdAndStatusIsTrue(it_id)
        synchronized(this) {
            val PO = po_repository.save(
                Purchase_Order(
                    id = 0,
                    employee = emp,
                    order_date = req.order_date,
                    active = "Out Stock",
                    create_by = req.create_by,
                    description = req.description
                )
            )
            purchaseOrderDetailrepository.save(
                Purchase_Order_Detail(
                    id = 0,
                    product = pro,
                    itemVariantUom = itm,
                    qty = req.qty ,
                    purchaseOrder = PO,
                    price = req.price
                )
            )
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }

    override fun updatePO(emp_id: Int, pro_id: Int, item_id: Int ,id: Int, req: Purchase_OrderRequest): MutableMap<String, Any>? {
        var po = po_repository.findByIdAndStatusIsTrue(id)
        var pod = purchaseOrderDetailrepository.findByIdAndStatusIsTrue(id)
        var emp = employeeRepository.findByIdAndStatusTrue(emp_id)
        var pro = productRepository.findByIdAndStatusIsTrue(pro_id)
        var itm = itemVeriantUomRepository.findByIdAndStatusIsTrue(item_id)
        synchronized(this){
            po?.id ?:  0
            po?.employee = emp
            po?.order_date = req.order_date
            po?.active?: "Out Stock"
            po?.create_by =  req.create_by
            po?.description = req.description
            var Purchase = po_repository.save(po!!)

            pod?.id?: 0
            pod?.product = pro
            pod?.itemVariantUom = itm
            pod?.qty = req.qty
            pod?.purchaseOrder = Purchase
            pod?.price = req.price
            purchaseOrderDetailrepository.save(pod!!)
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }


}