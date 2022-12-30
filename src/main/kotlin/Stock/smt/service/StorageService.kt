package Stock.smt.service

import Stock.smt.response.FileUploadResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

interface StorageService {

    fun storeImageFile(filePath:String,file: MultipartFile): FileUploadResponse
    fun loadImageFile(fileName: String,fileProperty: String,httpServletRequest: HttpServletRequest): ResponseEntity<*>?
    fun removeFileImage(name: String, pathFile: String): Boolean?

}