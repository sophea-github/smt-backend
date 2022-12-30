package Stock.smt.repository

import Stock.smt.model.SaleDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleDetailRepository: JpaRepository<SaleDetail, Int> {
    fun findByIdAndStatusIsTrue(id: Int): SaleDetail?
}