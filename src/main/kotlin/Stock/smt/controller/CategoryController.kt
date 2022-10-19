package Stock.smt.controller

import Stock.smt.model.Category
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.CategoryService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class CategoryController constructor (
    var categoryService: CategoryService,
    var responseObjectMap: ResponseObjectMap
        )
{
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    fun findAllList(@RequestParam(required = false) q: String?, page: Int, size: Int): MutableMap<String, Any> {
        val cat = categoryService.pagination(q, page, size)
        return responseObjectMap.responseOBJ(cat.totalElements, cat.content)
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category/{id}")
    fun findById(@PathVariable id: Int):MutableMap<String,Any> = responseObjectMap.responseObj(categoryService.findCategoryById(id)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category")
    fun findAll() : MutableMap<String,Any> = responseObjectMap.responseObj(categoryService.findAll()!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/category")
    fun saveAll(@RequestBody category: Category) : MutableMap<String,Any> = responseObjectMap.responseObj(categoryService.saveAll(category)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/category/{id}")
    fun updateCategory(@PathVariable id: Int, @RequestBody category: Category) : MutableMap<String , Any> = responseObjectMap.responseObj(categoryService.updateObj(id,category)!!)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("category/{id}")
    fun deleteCategory(@PathVariable id: Int) : MutableMap<String, Any> = responseObjectMap.responseObj(categoryService.delete(id))
}