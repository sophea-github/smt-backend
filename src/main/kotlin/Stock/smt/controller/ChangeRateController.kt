package Stock.smt.controller

import Stock.smt.model.ChangeRate
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.ChangeRateService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/v1")
@CrossOrigin
class ChangeRateController constructor(
    var changeRateservice: ChangeRateService,
    var responseObjectMap: ResponseObjectMap
) {
    @PreAuthorize("hasAnyRole('ADMIN','User')")
    @GetMapping("/changeRate")
    fun getAll(): MutableMap<String,Any> = responseObjectMap.responseObj(changeRateservice.findAll()!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/changeRate")
    fun save(@RequestBody t: ChangeRate): MutableMap<String,Any> = responseObjectMap.responseObj(changeRateservice.saveAll(t)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changeRate/{id}")
    fun update(@PathVariable id: Int,@RequestBody t: ChangeRate): MutableMap<String,Any> =  responseObjectMap.responseObj(changeRateservice.updateObj(id,t)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/changeRate/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(changeRateservice.delete(id))
}