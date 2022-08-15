package Stock.smt.service.serviceImpl

import Stock.smt.model.Category
import Stock.smt.model.SubCategory
import Stock.smt.repository.CategoryRepository
import Stock.smt.repository.SubCategoryRepository
import Stock.smt.service.SubCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SubCategoryServiceImpl : SubCategoryService {
    @Autowired
    lateinit var subCategoryRepository: SubCategoryRepository
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    @Autowired
    lateinit var subCategoryService: SubCategoryService

    override fun pagination(q: String?, page: Int, size: Int): Page<SubCategory> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<SubCategory>? = subCategoryRepository.findAll()

    override fun saveAll(t: SubCategory): SubCategory? {
       return subCategoryRepository.save(t)
    }

    override fun updateObj(id: Int, t: SubCategory): SubCategory? {
//        TODO("")
        var sub = subCategoryRepository.findByIdAndStatusTrue(id)
        sub?.sub_name = t.sub_name
        sub?.category = t.category
        sub?.description = t.description
        return subCategoryRepository.save(sub!!)
    }

    override fun delete(id: Int) = subCategoryRepository.deleteById(id)

    override fun createSubCategories(id: Int, t: SubCategory): SubCategory? {
        var red: SubCategory? = subCategoryService.saveAll(t)
        var ct_id= categoryRepository.findByIdAndStatusTrue(id)
        red?.sub_name = t.sub_name
        red?.description =t.description
//        red?.body =t.body
        red?.category =ct_id
        return subCategoryRepository.save(red!!)
    }

    override fun updateByCategoryId(cat_id: Int, id: Int, t: SubCategory): SubCategory? {
        var cid= categoryRepository.findByIdAndStatusTrue(cat_id)
        var sub = subCategoryRepository.findByIdAndStatusTrue(id)
//            ?: throw ResourceNotFoundException("Id Not Found $id")
        sub?.sub_name =t.sub_name
        sub?.description = t.description
        sub?.category = cid
        return subCategoryRepository.save(sub!!)
    }

     override fun getCategoryId(id: Int): MutableList<Category>? {
        return  subCategoryRepository.findCategoryById(id)
    }
}