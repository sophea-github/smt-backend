package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.ItemVariantUom
import Stock.smt.service.ItemVeriantUomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class ItemVeriantUomController {
    @Autowired
    lateinit var uomDetailservice: ItemVeriantUomService
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    @GetMapping("/uom_detail")
    fun findAll(): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomDetailservice.findAll()!!)
    @PostMapping("/uom_detail/{id}")
    fun save(@PathVariable id:Int, @RequestBody t: ItemVariantUom): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomDetailservice.createUom_Detail(id,t)!!)
    @PutMapping("/uom_detail/{um_id}/{id}")
    fun update(@PathVariable um_id: Int, @PathVariable id: Int,@RequestBody uomDetail: ItemVariantUom): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomDetailservice.updateUom_Detail(um_id,id,uomDetail)!!)
    @DeleteMapping("/uom_detail/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(uomDetailservice.delete(id))
}