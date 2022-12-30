package Stock.smt.repository

import Stock.smt.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Int> {
    fun findByIdAndStatusIsTrue(id: Int): Customer?
    fun findCustomerById(id: Int): Customer?
}