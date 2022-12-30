package Stock.smt.service.serviceImpl

import Stock.smt.response.FileUploadResponse
import Stock.smt.service.StorageService
import Stock.smt.util.FileUpload
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@Service
class StorageServiceImpl: StorageService {
    override fun storeImageFile(filePath: String, file: MultipartFile): FileUploadResponse {
        val fileUpload = FileUpload.storeImage(file,filePath)
        return FileUploadResponse(
            file = filePath.substring(1) + "/" + fileUpload?.file,
            fileName = fileUpload?.fileName,
            filePath = filePath.substring(1) + "/",
            fileSize = fileUpload?.fileSize
        )
    }
    override fun removeFileImage(name: String, pathFile: String): Boolean? {
        return FileUpload.removeImage(name,pathFile)
    }
    override fun loadImageFile(fileName: String, fileProperty: String, httpServletRequest: HttpServletRequest
    ): ResponseEntity<*>? {
        return try {
            FileUpload.loadFileImage(fileName, fileProperty, httpServletRequest)
        } catch (ex: Exception) {
            null
        }
    }
}