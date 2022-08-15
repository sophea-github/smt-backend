package Stock.smt.repository

import Stock.smt.model.Purchase_Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Purchase_OrderRepository: JpaRepository<Purchase_Order, Int> {

    fun findByIdAndStatusIsTrue(id: Int): Purchase_Order?
}