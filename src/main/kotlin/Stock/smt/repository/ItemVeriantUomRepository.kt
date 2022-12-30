package Stock.smt.repository

import Stock.smt.model.ItemVariantUom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemVeriantUomRepository: JpaRepository<ItemVariantUom,Int> {
    fun findByIdAndStatusIsTrue(id: Int): ItemVariantUom?
    fun deleteByIdAndStatusIsTrue(id: Int)
}