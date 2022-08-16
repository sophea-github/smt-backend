package Stock.smt.repository

import Stock.smt.model.Change_Rate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Change_RateRepository: JpaRepository<Change_Rate,Int> {
    fun findByIdAndStatusIsTrue(id:Int): Change_Rate?
}