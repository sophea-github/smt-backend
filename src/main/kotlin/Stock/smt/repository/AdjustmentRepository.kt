package Stock.smt.repository

import Stock.smt.model.Adjustment
import Stock.smt.model.AdjustmentDetail
import Stock.smt.model.Custom.DTO.AdDTO
import Stock.smt.model.Custom.DTO.AdjustmentDTO
import Stock.smt.model.Custom.DTO.AdjustmentRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface AdjustmentRepository: JpaRepository<Adjustment,Int> {

    fun findByIdAndStatusIsTrue(id: Int): Adjustment?

//    @Modifying
    @Query("SELECT ad FROM Adjustment ad JOIN AdjustmentDetail adt ON ad.id = adt.adjustment.id WHERE ad.id = ?1")
    @Transactional
    fun findAdjustment(id: Int): AdjustmentDTO?

    @Query("SELECT ad.id as id ,ad.code as code, ad.adjustmentDate as adjustmentDate , ad.adjustmentType.type as type ,ad.description as description , " +
            " COUNT(adt.product) as total FROM Adjustment ad JOIN AdjustmentDetail adt ON ad.id = adt.adjustment.id " +
            "GROUP BY id, code,adjustmentDate , type,description ")
    @Transactional
    fun findCountAdjustment(): List<AdDTO>
}