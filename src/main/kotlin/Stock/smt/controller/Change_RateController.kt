package Stock.smt.controller

import Stock.smt.model.Change_Rate
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.Change_RateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@CrossOrigin
class Change_RateController constructor(
    var changeRateservice: Change_RateService,
    var responseObjectMap: ResponseObjectMap
) {
    @GetMapping("/changeRate")
    fun getAll(): MutableMap<String,Any> = responseObjectMap.ResponseObj(changeRateservice.findAll()!!)
    @PostMapping("/changeRate")
    fun save(@RequestBody t: Change_Rate): MutableMap<String,Any> = responseObjectMap.ResponseObj(changeRateservice.saveAll(t)!!)
    @PutMapping("/changeRate/{id}")
    fun update(@PathVariable id: Int,@RequestBody t: Change_Rate): MutableMap<String,Any> =  responseObjectMap.ResponseObj(changeRateservice.updateObj(id,t)!!)
    @DeleteMapping("/changeRate/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.ResponseObj(changeRateservice.delete(id)!!)
}