package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Category
import Stock.smt.model.SubCategory

interface SubCategoryService : BaseFun<SubCategory,Int> {

    fun createSubCategories(id: Int ,subCategory: SubCategory): SubCategory?
    fun getCategoryId(id:Int): MutableList<Category>?
    fun updateByCategoryId(cat_id: Int,id: Int , subCategory: SubCategory): SubCategory?
}