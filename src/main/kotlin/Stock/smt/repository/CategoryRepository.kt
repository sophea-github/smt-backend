package Stock.smt.repository

import Stock.smt.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository


@Repository
interface CategoryRepository : JpaRepository<Category,Int>,JpaSpecificationExecutor<Category> {

    fun findByIdAndStatusTrue(id: Int) : Category?


}