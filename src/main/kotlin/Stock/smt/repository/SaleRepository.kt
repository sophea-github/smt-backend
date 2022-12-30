package Stock.smt.repository

import Stock.smt.model.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface SaleRepository : JpaRepository<Sale,Int>{
    fun findByIdAndStatusIsTrue(id: Int): Sale?
//    @Query("SELECT sl FROM Sale sl JOIN SaleDetail sd ON sl.id = sd.sale.id")
//    @Transactional
//    fun findAllSale(): List<Sale>
    @Modifying
    @Query("DELETE FROM SaleDetail sd WHERE sd.sale.id = ?1")
    @Transactional
    fun deleteSaleById(slId: Int)
}