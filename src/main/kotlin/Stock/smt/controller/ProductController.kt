package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Product
import Stock.smt.repository.ProductRepository
import Stock.smt.service.ProductService
import Stock.smt.util.ProductExport
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class ProductController constructor(
    var productService: ProductService,
    var responseObjectMap: ResponseObjectMap,
    var productRepository: ProductRepository
) {

    @GetMapping("/product")
    fun getAll(): MutableMap<String , Any> = responseObjectMap.responseObj(productService.findAll()!!)

    @GetMapping("/product/{id}")
    fun getById(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.responseObj(productService.findProductById(id)!!)

    @PostMapping("/product/{catId}/{itmId}")
    fun save(@PathVariable catId: Int, @PathVariable itmId: Int, @RequestBody t: Product): MutableMap<String,Any> {
        if(!productRepository.existsByCode(t.code)){
            return responseObjectMap.responseOBJ(200,productService.createProduct(catId,itmId,t)!!)
        }else if (productRepository.existsByCode(t.code)){
            return responseObjectMap.responseOBJ(500,"Duplicate")
        }else{
            return responseObjectMap.responseOBJ(400,"Check Field Again")
        }
    }

    @PutMapping("/product/{catId}/{itmId}/{id}")
    fun update(@PathVariable catId: Int, @PathVariable itmId: Int, @PathVariable id: Int, @RequestBody t: Product): MutableMap<String,Any> {
        return responseObjectMap.responseObj(productService.updateProduct(catId,itmId,id,t)!!)
    }

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(productService.delete(id))

    @PostMapping("/product/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.responseObj(productService.uploadImg(id,photo)!!)

    @GetMapping("/product/export")
    fun exportToExcel(response: HttpServletResponse) {
//        response.contentType = "application/octet-stream";

        val formatter= SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val currentDateTime = formatter.format(Date())
        var headerKey = "Content-Disposition"
        val headerValue = "attachment;filename_Product$currentDateTime.xlsx"
//        response.setHeader(headerKey, headerValue)
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        response.setHeader("Content-Disposition", "attachment; filename=Product_$currentDateTime.xlsx")
        val list: List<Product>? = productService.findAll()
        val exportToExcel = ProductExport(list)
        exportToExcel.export(response)
    }

    @PutMapping("/product/{id}/{qty}")
    fun updateStockProduct(@PathVariable id: Int, @PathVariable qty: Int):MutableMap<String,Any> = responseObjectMap.responseObj(productService.updateStockProduct(id, qty))

}