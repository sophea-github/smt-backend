package Stock.smt.repository

import Stock.smt.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupplierRepository : JpaRepository<Supplier, Int> {
    fun findByIdAndStatusTrue(id: Int): Supplier?
    fun findByContactOrEmail(contact: String, email: String): Supplier?
//    fun findByContactOrEmail(contact: String, email: String): Supplier?
    fun findByContact(contact: String): Supplier?
    fun findByEmail(email: String): Supplier?
}