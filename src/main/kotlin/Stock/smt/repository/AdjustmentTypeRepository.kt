package Stock.smt.repository

import Stock.smt.model.AdjustmentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdjustmentTypeRepository: JpaRepository<AdjustmentType, Int> {
    fun findByIdAndStatusIsTrue(id: Int): AdjustmentType
}