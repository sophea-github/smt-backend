package Stock.smt.repository

import Stock.smt.model.AdjustmentDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
@Repository
interface AdjustmentDetailRepository: JpaRepository<AdjustmentDetail, Any> {
    fun findByIdAndStatusIsTrue(id: Int): AdjustmentDetail?
    @Modifying
    @Query("DELETE FROM AdjustmentDetail ad WHERE ad.adjustment.id = ?1")
    @Transactional
    fun deleteAdjById(adId: Int)
}