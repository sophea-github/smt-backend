package Stock.smt.repository

import Stock.smt.model.Category
import Stock.smt.model.SubCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface SubCategoryRepository : JpaRepository<SubCategory,Int> {
    fun findByIdAndStatusTrue(id: Int): SubCategory?
    @Transactional
    @Query("SELECT * FROM category WHERE id= :cat_id",nativeQuery = true)
    fun findCategoryById(cat_id: Int): MutableList<Category>?
}