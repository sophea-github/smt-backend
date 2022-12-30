package Stock.smt.repository

import Stock.smt.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupplierRepository : JpaRepository<Supplier, Int> {
    fun findByIdAndStatusTrue(id: Int): Supplier?
}