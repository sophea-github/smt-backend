package Stock.smt.service.serviceImpl

import Stock.smt.model.Category
import Stock.smt.repository.CategoryRepository
import Stock.smt.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.criteria.Predicate

@Service
class CategoryServiceImpl: CategoryService {
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    override fun pagination(q: String?, page: Int, size: Int): Page<Category> {
        return categoryRepository.findAll(
            {root, query, cd ->
                val predicates = ArrayList<Predicate>()
                q?.let {
                    val catName = cd.like(cd.upper(root.get("name")), "%${q.uppercase(Locale.getDefault())}%")
                    predicates.add(cd.or(catName))
                }
                query.orderBy(cd.desc(root.get<String>("id")))
                cd.and(*predicates.toTypedArray())
            },
            PageRequest.of(page, size))
    }
    override fun findAll(): List<Category>? = categoryRepository.findAll()
    override fun saveAll(t: Category): Category? = categoryRepository.save(t)
    override fun updateObj(id: Int, t: Category): Category? {
        val cat= categoryRepository.findByIdAndStatusTrue(id)
        cat?.name = t.name
        cat?.description = t.description
        return categoryRepository.save(cat!!)
    }
    override fun delete(id: Int) {
        try {
            categoryRepository.deleteById(id)
        }catch (e: Exception){

        }
    }
    override fun findCategoryById(id: Int): Category? {
        return categoryRepository.findByIdAndStatusTrue(id)
    }
}