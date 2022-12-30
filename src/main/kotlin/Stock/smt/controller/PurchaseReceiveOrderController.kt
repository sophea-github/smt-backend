package Stock.smt.controller

import Stock.smt.model.custom.dto.PurchaseReceiveOrderRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.PurchaseReceiveOrderService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
@RequestMapping("/api/v1")
@CrossOrigin
class PurchaseReceiveOrderController constructor(
    var responseObjectMap: ResponseObjectMap,
    var purchaseReceiveOrderService: PurchaseReceiveOrderService
    ) {

    @GetMapping("/PurchaseReceive")
    fun findAllPor(): MutableMap<String,Any>{
        return responseObjectMap.responseObj(purchaseReceiveOrderService.findAllPor())
    }
    @GetMapping("PurchaseReceive/por/{id}")
    fun findPor(@PathVariable id: Int): MutableMap<String,Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.findPorDetail(id))
    }

    @GetMapping("PurchaseReceive/{proId}/{code}")
    fun findPro(@PathVariable proId: Int,@PathVariable code: String ): MutableMap<String,Any>{
        val p = purchaseReceiveOrderService.findProOrderDetail(proId, code)
            ?: return responseObjectMap.responseOBJ(500,"Not Found !!")
        return responseObjectMap.responseOBJ(100,purchaseReceiveOrderService.findProOrderDetail(proId,code)!!)
    }

    @PostMapping("/PurchaseReceive/{empId}")
    fun addPor(@PathVariable empId: Int,@RequestBody req: PurchaseReceiveOrderRequest): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.addPurchaseReceive(empId,req)!!)
    }
    @PutMapping("/PurchaseReceive/{empId}/{crId}/{porId}")
    fun updatePor(@PathVariable empId: Int, @PathVariable crId: Int, @PathVariable porId: Int,@RequestBody req: PurchaseReceiveOrderRequest): MutableMap<String, Any>{
        return responseObjectMap.responseObj(purchaseReceiveOrderService.updatePurchaseReceive(empId,crId,porId,req)!!)
    }

    @DeleteMapping("PurchaseReceive/{id}")
    fun deleteById(@PathVariable id: Int) : MutableMap<String, Any>{
        return responseObjectMap.responseObj(purchaseReceiveOrderService.deletePor(id))
    }

}