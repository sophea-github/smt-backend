package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.Supplier
import Stock.smt.service.SupplierService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController

@RequestMapping("/api/v1")
@CrossOrigin
class SupplierController constructor(
    var supplierService: SupplierService,
    var responseObjectMap: ResponseObjectMap,
) {
//    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("/supplier")
    fun findAll(): MutableMap<String, Any> = responseObjectMap.responseObj(supplierService.findAll()!!)

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @PostMapping("/supplier")
    fun saveAll(@RequestBody supplier: Supplier): MutableMap<String, Any> =
        responseObjectMap.responseObject(supplierService.addSupplier(supplier)!!)

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/supplier/{id}")
    fun updateObj(@PathVariable id: Int, @RequestBody supplier: Supplier): MutableMap<String, Any> =
        responseObjectMap.responseObj(supplierService.updateSupplier(id, supplier)!!)

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/supplier/{id}")
    fun deleteSupplier(@PathVariable id: Int): MutableMap<String, Any> =
        responseObjectMap.responseObj(supplierService.delete(id))

}