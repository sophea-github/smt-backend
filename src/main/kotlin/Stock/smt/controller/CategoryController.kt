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
    @GetMapping("/list")
    fun findAllList(@RequestParam(required = false) q: String?, page: Int, size: Int): MutableMap<String, Any> {
        val cat = categoryService.pagination(q, page, size)
        return responseObjectMap.responseOBJ(cat.totalElements, cat.content)
    }
    @GetMapping("/category/{id}")
    fun findById(@PathVariable id: Int):MutableMap<String,Any> = responseObjectMap.ResponseObj(categoryService.findCategoryById(id)!!)

    @GetMapping("/category")
    fun findAll() : MutableMap<String,Any> = responseObjectMap.ResponseObj(categoryService.findAll()!!)

    @PostMapping("/category")
    fun saveAll(@RequestBody category: Category) : MutableMap<String,Any> = responseObjectMap.ResponseObj(categoryService.saveAll(category)!!)
    @PutMapping("/category/{id}")
    fun updateCategory(@PathVariable id: Int, @RequestBody category: Category) : MutableMap<String , Any> = responseObjectMap.ResponseObj(categoryService.updateObj(id,category)!!)

    @DeleteMapping("category/{id}")
    fun deleteCategory(@PathVariable id: Int) : MutableMap<String, Any> = responseObjectMap.ResponseObj(categoryService.delete(id)!!)
}