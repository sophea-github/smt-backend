package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Employee
import Stock.smt.service.EmployeeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class EmployeeController constructor(
    var responseObjectMap: ResponseObjectMap,
    var employeeService: EmployeeService
) {
    @GetMapping("/employee")
    fun getAll(): MutableMap<String, Any> = responseObjectMap.responseObj(employeeService.findAll()!!)
    @GetMapping("/employee/{id}")
    fun getById(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.findById(id)!!)
    @PostMapping("/employee")
    fun saveAll(@RequestBody employee: Employee): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.saveAll(employee)!!)
    @PutMapping("/employee/{id}")
    fun updateObj(@PathVariable id: Int, @RequestBody employee: Employee): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.updateObj(id,employee)!!)
    @DeleteMapping("/employee/{id}")
    fun deleteObj(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.delete(id))
    @PostMapping("/employee/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.uploadImg(id,photo)!!)

}