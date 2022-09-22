package Stock.smt.controller

import Stock.smt.model.Custom.DTO.PurchaseReceiveOrderRequest
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.PurchaseReceiveOrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class PurchaseReceiveOrderController constructor(
    var responseObjectMap: ResponseObjectMap,
    var purchaseReceiveOrderService: PurchaseReceiveOrderService
    ) {


    @GetMapping("/PurchaseReceive")
    fun findAllPor(): MutableMap<String,Any>{
        return responseObjectMap.responseObj(purchaseReceiveOrderService.findAllPor())
    }

    @GetMapping("PurchaseReceive/{proId}")
    fun findPro(@PathVariable proId: Int): MutableMap<String,Any>{
        val p = purchaseReceiveOrderService.findProOrderDetail(proId)
            ?: return responseObjectMap.responseOBJ(500,"Not Found !!")
        return responseObjectMap.responseOBJ(100,purchaseReceiveOrderService.findProOrderDetail(proId)!!)
    }

    @PostMapping("/PurchaseReceive/{empId}/{crId}")
    fun addPor(@PathVariable empId: Int, @PathVariable crId: Int,@RequestBody req: PurchaseReceiveOrderRequest): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.addPurchaseReceive(empId,crId,req)!!)
    }
    @PutMapping("/PurchaseReceive/{empId}/{crId}/{porId}")
    fun updatePor(@PathVariable empId: Int, @PathVariable crId: Int, @PathVariable porId: Int,@RequestBody req: PurchaseReceiveOrderRequest): MutableMap<String, Any>{
        return responseObjectMap.responseObj(purchaseReceiveOrderService.updatePurchaseReceive(empId,crId,porId,req)!!)
    }

}