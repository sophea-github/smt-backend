package Stock.smt.controller

import Stock.smt.model.custom.dto.AdjustmentRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.AdjustmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class AdjustmentController {
    @Autowired lateinit var adjustmentService: AdjustmentService
    @Autowired lateinit var responseObjectMap: ResponseObjectMap

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/adjustment/{id}")
    fun findAll(@PathVariable id:Int): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentService.findAdjustment(id)!!)
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/adjustment")
    fun findCountAdjustment(): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentService.findCountAdjustment())
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adjustment/{adjTypeId}")
    fun save(@PathVariable adjTypeId: Int , @RequestBody req: AdjustmentRequest): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentService.addAdjustment(adjTypeId,req)!!)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adjustment/{adjTypeId}/{id}")
    fun update(@PathVariable adjTypeId: Int,@PathVariable id: Int,@RequestBody req: AdjustmentRequest): MutableMap<String, Any>{
        return responseObjectMap.responseObj(adjustmentService.updateAdjustment(adjTypeId,id,req)!!)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adjustment/{adId}")
    fun delete(@PathVariable adId: Int): MutableMap<String,Any>{
        return responseObjectMap.responseObj(adjustmentService.deleteAdjustment(adId)!!)
    }
}