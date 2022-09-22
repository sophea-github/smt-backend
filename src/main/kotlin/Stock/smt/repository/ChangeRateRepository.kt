package Stock.smt.repository

import Stock.smt.model.ChangeRate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChangeRateRepository: JpaRepository<ChangeRate,Int> {
    fun findByIdAndStatusIsTrue(id:Int): ChangeRate?
}