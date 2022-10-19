package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Uom
import Stock.smt.service.UomService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController

@RequestMapping("/api/v1")
@CrossOrigin
class UomController constructor(
    var uomService: UomService,
    var responseObjectMap: ResponseObjectMap
) {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/uom")
    fun findAll(): MutableMap<String, Any> = responseObjectMap.responseObj(uomService.findAll()!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/uom")
    fun save(@RequestBody uom: Uom): MutableMap<String,Any>  = responseObjectMap.responseObj(uomService.saveAll(uom)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/uom/{id}")
    fun update(@PathVariable id: Int, @RequestBody uom: Uom): MutableMap<String,Any> = responseObjectMap.responseObj(uomService.updateObj(id,uom)!!)
//    @DeleteMapping("/uom/{id}")
//    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomService.delete(id)!!)
}