package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.SubCategory
import Stock.smt.service.SubCategoryService
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class SubCategoryController constructor(
    var subCategoryService: SubCategoryService,
    var responseObjectMap: ResponseObjectMap
) {

    @GetMapping("/subCategories")
    fun findAll() : MutableMap<String,Any> = responseObjectMap.responseObj(subCategoryService.findAll()!!)
    @GetMapping("subCategories/{id}")
    fun getCategoryId(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(subCategoryService.getCategoryId(id)!!)
    @PostMapping("/subCategories")
    fun saveAll(@RequestBody subCategory: SubCategory) : MutableMap<String,Any> = responseObjectMap.responseObj(subCategoryService.saveAll(subCategory)!!)
    @PutMapping("/subCategories/{id}")
    fun updateCategory(@PathVariable id: Int, @RequestBody subCategory: SubCategory) : MutableMap<String , Any> = responseObjectMap.responseObj(subCategoryService.updateObj(id,subCategory)!!)
    @DeleteMapping("/subCategories/{id}")
    fun deleteCategory(@PathVariable id: Int) : MutableMap<String, Any> = responseObjectMap.responseObj(subCategoryService.delete(id))
    @PostMapping("/subCategories/{cid}")
    fun createSub(@PathVariable cid: Int , @RequestBody subCategory: SubCategory): MutableMap<String,Any> = responseObjectMap.responseObj(subCategoryService.createSubCategories(cid,subCategory)!!)
}