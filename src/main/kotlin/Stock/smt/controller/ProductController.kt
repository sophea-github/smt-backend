package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Product
import Stock.smt.repository.ProductRepository
import Stock.smt.service.ProductService
import Stock.smt.util.ProductExport
import org.springframework.boot.logging.java.SimpleFormatter
import org.springframework.format.datetime.DateFormatter
import org.springframework.web.bind.annotation.*
import java.io.IOException
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
    fun getAll(): MutableMap<String , Any> = responseObjectMap.ResponseObj(productService.findAll()!!)

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
        return responseObjectMap.ResponseObj(productService.updateProduct(catId,itmId,id,t)!!)
    }

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.ResponseObj(productService.delete(id))

    @PostMapping("/product/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.ResponseObj(productService.uploadImg(id,photo)!!)

    @GetMapping("/product/export")
    fun exportToExcel(response: HttpServletResponse) {
//        response.contentType = "application/octet-stream";

        val formatter= SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        var currentDateTime = formatter.format(Date());
        val headerkey = "Content-Disposition";
        val headerValue = "attachment;filename_Product$currentDateTime.xlsx";
//        response.setHeader(headerkey, headerValue);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Product_$currentDateTime.xlsx");
        val list: List<Product>? = productService.findAll()
        val exportToExcel = ProductExport(list)
        exportToExcel.export(response)
    }

}