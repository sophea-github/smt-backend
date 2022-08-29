package Stock.smt.controller

import Stock.smt.model.Custom.DTO.Purchase_OrderRequest
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Purchase_Order
import Stock.smt.repository.Purchase_Order_DetailRepository
import Stock.smt.service.Purchase_OrderService
import Stock.smt.service.Purchase_Order_DetailService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class Purchase_OrderController constructor(
    var purchaseOrderservice: Purchase_OrderService,
    var responseObjectMap: ResponseObjectMap,
   var purchaseOrderDetailservice: Purchase_Order_DetailService
){
    @GetMapping("/PurchaseOrder")
    fun findAllPod():MutableMap<String,Any> = responseObjectMap.ResponseObj(purchaseOrderDetailservice.findAll()!!)
    @PostMapping("/PurchaseOrder/{emp_id}/{pro_id}/{item_id}")
    fun save(@PathVariable emp_id: Int,@PathVariable pro_id: Int,@PathVariable item_id: Int,@RequestBody req: Purchase_OrderRequest): MutableMap<String, Any> = responseObjectMap.ResponseObj(purchaseOrderservice.addPO(emp_id,pro_id,item_id,req)!!)
    @PutMapping("/PurchaseOrder/{emp_id}/{pro_id}/{item_id}/{id}")
    fun update(@PathVariable emp_id: Int, @PathVariable pro_id: Int, @PathVariable item_id: Int, @PathVariable id: Int, @RequestBody req: Purchase_OrderRequest): MutableMap<String, Any> = responseObjectMap.ResponseObj(purchaseOrderservice.updatePO(emp_id,pro_id,item_id,id,req)!!)
    @DeleteMapping("/PurchaseOrder/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.ResponseObj(purchaseOrderservice.delete(id)!!)
}