package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Category

interface CategoryService : BaseFun<Category,Int> {


    fun findCategoryById(id: Int): Category?

}