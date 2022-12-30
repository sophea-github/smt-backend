package Stock.smt.repository

import Stock.smt.model.Adjustment
import Stock.smt.model.custom.dto.AdDTO
import Stock.smt.model.custom.dto.AdjustmentDTO
import Stock.smt.model.custom.dto.ReportAdjDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface AdjustmentRepository: JpaRepository<Adjustment,Int> {
    fun findByIdAndStatusIsTrue(id: Int): Adjustment?
    @Query("SELECT ad FROM Adjustment ad JOIN AdjustmentDetail adt ON ad.id = adt.adjustment.id WHERE ad.id = ?1")
    @Transactional
    fun findAdjustment(id: Int): AdjustmentDTO?
    @Query("SELECT ad.id as id ,ad.code as code, ad.adjustmentDate as adjustmentDate , ad.adjustmentType.type as type ,ad.description as description , " +
            " COUNT(adt.product) as total FROM Adjustment ad JOIN AdjustmentDetail adt ON ad.id = adt.adjustment.id " +
            "GROUP BY id, code,adjustmentDate , type,description ")
    @Transactional
    fun findCountAdjustment(): List<AdDTO>
    @Query("""
        select 
        pd.code AS code ,pd.name As name, ct.name as category, itm.item_variant_name AS item_variant_name ,sum(ajd.qty) as adjustment ,pd.qty as product from Product pd 
        join AdjustmentDetail ajd on ajd.product.id = pd.id 
        join Category ct on pd.category.id = ct.id
        join Adjustment ad on ajd.adjustment.id = ad.id
        join AdjustmentType adt on ad.adjustmentType.id = adt.id 
        join Employee emp on ajd.employee.id = emp.id 
        join ItemVariantUom itm on pd.itemVariantUom.id = itm.id
        where adt.type = :adjustment
        and ad.adjustmentDate >= :startDate 
        and ad.adjustmentDate <= :endDate
        group by pd.code,adt.type,pd.code,pd.name, ct.name,pd.qty,itm.item_variant_name
    """)
    fun adjustmentReport(adjustment: String, startDate: Date, endDate: Date): List<ReportAdjDto>
}