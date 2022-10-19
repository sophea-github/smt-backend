package Stock.smt.repository

import Stock.smt.model.DocumentarySetting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentarySettingRepository: JpaRepository<DocumentarySetting, Int> {
    fun findByIdAndStatusIsTrue(id: Int): DocumentarySetting
}