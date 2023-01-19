package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Employee
import Stock.smt.model.Supplier

interface EmployeeService : BaseFun<Employee,Int> {
    fun uploadImg(id: Int,photo: String): Employee?
    fun findById(id: Int): Employee?
    fun addEmployee(t: Employee): MutableMap<String,Any>?
    fun updateEmployee(id: Int,t: Employee): MutableMap<String,Any>?

}