package Stock.smt.repository

import Stock.smt.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Int> {
    fun findByIdAndStatusTrue(id: Int): Employee?
}