package Stock.smt.controller

import Stock.smt.model.ChangeRate
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.ChangeRateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@CrossOrigin
class ChangeRateController constructor(
    var changeRateservice: ChangeRateService,
    var responseObjectMap: ResponseObjectMap
) {
    @GetMapping("/changeRate")
    fun getAll(): MutableMap<String,Any> = responseObjectMap.responseObj(changeRateservice.findAll()!!)
    @PostMapping("/changeRate")
    fun save(@RequestBody t: ChangeRate): MutableMap<String,Any> = responseObjectMap.responseObj(changeRateservice.saveAll(t)!!)
    @PutMapping("/changeRate/{id}")
    fun update(@PathVariable id: Int,@RequestBody t: ChangeRate): MutableMap<String,Any> =  responseObjectMap.responseObj(changeRateservice.updateObj(id,t)!!)
    @DeleteMapping("/changeRate/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(changeRateservice.delete(id))
}