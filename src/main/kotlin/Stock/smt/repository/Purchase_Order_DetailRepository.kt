package Stock.smt.repository

import Stock.smt.model.Purchase_Order_Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Purchase_Order_DetailRepository: JpaRepository<Purchase_Order_Detail, Int> {

    fun findByIdAndStatusIsTrue(id: Int): Purchase_Order_Detail?
}