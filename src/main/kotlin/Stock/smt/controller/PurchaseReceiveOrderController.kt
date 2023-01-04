package Stock.smt.controller

import Stock.smt.model.custom.dto.PurchaseReceiveOrderRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.custom.exception.CustomBadRequestException
import Stock.smt.service.PurchaseReceiveOrderService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
@RequestMapping("/api/v1")
@CrossOrigin
class PurchaseReceiveOrderController constructor(
    var responseObjectMap: ResponseObjectMap,
    var purchaseReceiveOrderService: PurchaseReceiveOrderService,
) {
//    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE','USER')")
    @GetMapping("/PurchaseReceive")
    fun findAllPor(): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.findAllPor())
    }

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("PurchaseReceive/por/{id}")
    fun findPor(@PathVariable id: Int): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.findPorDetail(id))
    }

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("PurchaseReceive/{proId}/{code}/{suppId}")
    fun findPro(
        @PathVariable proId: Int,
        @PathVariable code: String,
        @PathVariable suppId: Int,
    ): MutableMap<String, Any> {
        val p = purchaseReceiveOrderService.findProOrderDetail(proId, code, suppId)
            ?: return responseObjectMap.responseOBJ(500, "Not Found !!")
        return responseObjectMap.responseOBJ(100, p)
    }
//    purchaseReceiveOrderService.findProOrderDetail(proId, code, suppId)

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @PostMapping("/PurchaseReceive/{empId}")
    fun addPor(@PathVariable empId: Int, @RequestBody req: PurchaseReceiveOrderRequest): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.addPurchaseReceive(empId, req)!!)
    }

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @PutMapping("/PurchaseReceive/{empId}/{crId}/{porId}")
    fun updatePor(
        @PathVariable empId: Int,
        @PathVariable crId: Int,
        @PathVariable porId: Int,
        @RequestBody req: PurchaseReceiveOrderRequest,
    ): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.updatePurchaseReceive(
            empId,
            crId,
            porId,
            req)!!)
    }

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @DeleteMapping("PurchaseReceive/{id}")
    fun deleteById(@PathVariable id: Int): MutableMap<String, Any> {
        return responseObjectMap.responseObj(purchaseReceiveOrderService.deletePor(id))
    }

}