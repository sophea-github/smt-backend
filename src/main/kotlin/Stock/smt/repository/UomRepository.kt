package Stock.smt.repository

import Stock.smt.model.Uom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UomRepository: JpaRepository<Uom,Int> {
    fun findByIdAndStatusIsTrue(id: Int): Uom?
}