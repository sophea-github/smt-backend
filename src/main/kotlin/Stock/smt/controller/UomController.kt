package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Uom
import Stock.smt.service.UomService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class UomController constructor(
    var uomService: UomService,
    var responseObjectMap: ResponseObjectMap
) {
    @GetMapping("/uom")
    fun findAll(): MutableMap<String, Any> = responseObjectMap.responseObj(uomService.findAll()!!)
    @PostMapping("/uom")
    fun save(@RequestBody uom: Uom): MutableMap<String,Any>  = responseObjectMap.responseObj(uomService.saveAll(uom)!!)
    @PutMapping("/uom/{id}")
    fun update(@PathVariable id: Int, @RequestBody uom: Uom): MutableMap<String,Any> = responseObjectMap.responseObj(uomService.updateObj(id,uom)!!)
//    @DeleteMapping("/uom/{id}")
//    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomService.delete(id)!!)
}