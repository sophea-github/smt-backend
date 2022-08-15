package Stock.smt.repository

import Stock.smt.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.persistence.Id

@Repository
interface ProductRepository : JpaRepository<Product, Int> {

    fun findByIdAndStatusIsTrue(id: Int): Product?
    fun existsByCode(code: String): Boolean
}