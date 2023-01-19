package Stock.smt.repository

import Stock.smt.model.Employee
import Stock.smt.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Int> {
    fun findByIdAndStatusTrue(id: Int): Employee?
    fun findByContactOrEmail(contact: String, email: String): Employee?
    fun findByContact(contact: String): Employee?
    fun findByEmail(email: String): Employee?
}