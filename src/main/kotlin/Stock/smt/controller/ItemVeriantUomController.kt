package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.ItemVariantUom
import Stock.smt.service.ItemVeriantUomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController

@RequestMapping("/api/v1")
@CrossOrigin
class ItemVariantUomController constructor(
    private val uomDetailService: ItemVeriantUomService,
    private val responseObjectMap: ResponseObjectMap
) {
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/uom_detail")
    fun findAll(): MutableMap<String,Any> = responseObjectMap.responseObj(uomDetailService.findAll()!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/uom_detail/{id}")
    fun save(@PathVariable id:Int, @RequestBody t: ItemVariantUom): MutableMap<String,Any> = responseObjectMap.responseObj(uomDetailService.createUomDetail(id,t)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/uom_detail/{um_id}/{id}")
    fun update(@PathVariable um_id: Int, @PathVariable id: Int,@RequestBody uomDetail: ItemVariantUom): MutableMap<String,Any> = responseObjectMap.responseObj(uomDetailService.updateUomDetail(um_id,id,uomDetail)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/uom_detail/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(uomDetailService.delete(id))
}