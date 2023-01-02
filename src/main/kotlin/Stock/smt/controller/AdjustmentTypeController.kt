package Stock.smt.controller

import Stock.smt.model.AdjustmentType
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.AdjustmentTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@CrossOrigin
class AdjustmentTypeController {
    @Autowired lateinit var adjustmentTypeService: AdjustmentTypeService
    @Autowired lateinit var responseObjectMap: ResponseObjectMap
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/adjustmentType")
    fun findAll(): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentTypeService.findAll()!!)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adjustmentType")
    fun save(@RequestBody t: AdjustmentType): MutableMap<String, Any>{
        return responseObjectMap.responseObj(adjustmentTypeService.saveAll(t)!!)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adjustmentType/{id}")
    fun update(@PathVariable id: Int, @RequestBody t: AdjustmentType): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentTypeService.updateObj(id,t)!!)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adjustmentType/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String , Any>{
        return responseObjectMap.responseObj(adjustmentTypeService.delete(id))
    }

}