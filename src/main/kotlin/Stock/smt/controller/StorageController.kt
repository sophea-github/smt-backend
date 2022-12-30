package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class StorageController {

    @Autowired
    lateinit var storageService: StorageService
    @Autowired
    lateinit var responseObjMap: ResponseObjectMap

    var resource = "./resources/images/"
//    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @PostMapping("/upload-image-/{type}")
    fun uploadFileImage(@PathVariable("type") type: String, file: MultipartFile): MutableMap<String, Any> {
        val path = type.replace('-', '/')
        return responseObjMap.responseObj(storageService.storeImageFile("$resource$path", file))
    }
//    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @GetMapping("/resources/images/{type}/{fileName:.+}")
    fun loadImage(
        @PathVariable fileName: String,
        @PathVariable type: String,
        request: HttpServletRequest
    ): ResponseEntity<*>? {
        return storageService.loadImageFile(fileName, "$resource$type", request)
    }
//    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @DeleteMapping("/delete")
    fun removeFileImage(@RequestParam name:String, @RequestParam filePath:String):MutableMap<String,Any>
    {
        return responseObjMap.responseObj(storageService.removeFileImage(name,filePath)!!)
    }
}