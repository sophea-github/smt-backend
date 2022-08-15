package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Employee

interface EmployeeService : BaseFun<Employee,Int> {
    fun uploadImg(id: Int,photo: String): Employee?
}