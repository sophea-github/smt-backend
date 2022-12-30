package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.Customer
import Stock.smt.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @GetMapping("/customer")
    fun getData(): MutableMap<String,Any> = responseObjectMap.responseObj(customerService.findAll()!!)
    @PostMapping("/customer")
    fun AddNew(@RequestBody t: Customer): MutableMap<String,Any> = responseObjectMap.responseObj(customerService.saveAll(t)!!)
    @PutMapping("/customer/{id}")
    fun updateData(@PathVariable id: Int, @RequestBody t: Customer): MutableMap<String,Any>{
        return responseObjectMap.responseObj(customerService.updateObj(id, t)!!)
    }
    @DeleteMapping("/customer/{id}")
    fun deleteData(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(customerService.delete(id))


}