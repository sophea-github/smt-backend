package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Supplier
import Stock.smt.service.SupplierService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class SupplierController constructor(
    var supplierService: SupplierService,
    var responseObjectMap: ResponseObjectMap
) {

    @GetMapping("/supplier")
    fun findAll(): MutableMap<String,Any> = responseObjectMap.ResponseObj(supplierService.findAll()!!)
    @PostMapping("/supplier")
    fun saveAll(@RequestBody supplier: Supplier): MutableMap<String,Any> = responseObjectMap.ResponseObj(supplierService.saveAll(supplier)!!)
    @PutMapping("/supplier/{id}")
    fun updateObj(@PathVariable id: Int, @RequestBody supplier: Supplier): MutableMap<String,Any> = responseObjectMap.ResponseObj(supplierService.updateObj(id,supplier)!!)
    @DeleteMapping("/supplier/{id}")
    fun deleteSupplier(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(supplierService.delete(id)!!)

}