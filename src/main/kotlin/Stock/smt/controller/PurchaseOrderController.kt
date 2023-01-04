package Stock.smt.controller

import Stock.smt.model.custom.dto.PurchaseOrderRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.PurchaseOrderService
import Stock.smt.service.PurchaseOrderDetailService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class PurchaseOrderController constructor(
    var purchaseOrderService: PurchaseOrderService,
    var responseObjectMap: ResponseObjectMap,
    var purchaseOrderDetailService: PurchaseOrderDetailService
){
//    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE','USER')")
    @GetMapping("/po")
    fun findAll():MutableMap<String,Any> = responseObjectMap.responseObj(purchaseOrderService.findAll()!!)
//    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("/PurchaseOrder")
    fun findAllPo():MutableMap<String,Any> = responseObjectMap.responseObj(purchaseOrderService.findAllPO()!!)
    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("/PurchaseOrderDetail/{poId}")
    fun findAllPod(@PathVariable poId: Int): MutableMap<String,Any> = responseObjectMap.responseObj(purchaseOrderService.findPo(poId)!!)
    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @PostMapping("/PurchaseOrder/{cr_id}/{emp_id}/{supId}")
    fun save(@PathVariable cr_id: Int, @PathVariable emp_id: Int, @PathVariable supId: Int, @RequestBody req: PurchaseOrderRequest): MutableMap<String, Any> = responseObjectMap.responseObj(purchaseOrderService.addPO(cr_id,emp_id,supId,req)!!)
    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @PutMapping("/PurchaseOrder/{emp_id}/{cr_id}/{supId}/{po_id}")
    fun update(@PathVariable emp_id: Int, @PathVariable cr_id: Int, @PathVariable supId: Int, @PathVariable po_id: Int, @RequestBody req: PurchaseOrderRequest): MutableMap<String, Any> = responseObjectMap.responseObj(purchaseOrderService.updatePO(emp_id,cr_id,supId,po_id,req)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/PurchaseOrder/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(purchaseOrderService.deletePO(id)!!)
}