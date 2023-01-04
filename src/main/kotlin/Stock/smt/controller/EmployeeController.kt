package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.Employee
import Stock.smt.service.EmployeeService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class EmployeeController constructor(
    var responseObjectMap: ResponseObjectMap,
    var employeeService: EmployeeService
) {
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    fun getAll(): MutableMap<String, Any> = responseObjectMap.responseObj(employeeService.findAll()!!)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employee/{id}")
    fun getById(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.findById(id)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    fun saveAll(@RequestBody employee: Employee): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.saveAll(employee)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{id}")
    fun updateObj(@PathVariable id: Int, @RequestBody employee: Employee): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.updateObj(id,employee)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{id}")
    fun deleteObj(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.delete(id))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.responseObj(employeeService.uploadImg(id,photo)!!)
}