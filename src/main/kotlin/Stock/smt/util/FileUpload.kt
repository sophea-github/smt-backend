package Stock.smt.util

import Stock.smt.response.FileUploadResponse
import Stock.smt.model.custom.exception.CustomBadRequestException
import Stock.smt.model.custom.exception.CustomNotAcceptableException
import Stock.smt.model.custom.exception.CustomNotFoundException
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FileUpload {

    companion object{

        fun storeImage(file: MultipartFile, filePath:String):FileUploadResponse?
        {
            var path = Paths.get(filePath).toAbsolutePath().normalize()
            var directory = File(path.toString())

            if(!directory.exists())
            {
                directory.mkdirs()
            }
            var extension = file.originalFilename.toString()
            var sub = extension.substring(extension.lastIndexOf("."))
            var formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
            var nameFile= UUID.randomUUID().toString().plus(formatter.format(Date()).toString().plus(sub))
            try {
                Files.copy(file.inputStream,path.resolve(nameFile), StandardCopyOption.REPLACE_EXISTING)
            }catch (io: IOException)
            {
                println("exception image file"+ io)
            }

            return FileUploadResponse(nameFile,extension,filePath,file.size)
        }
        fun storeFile(file: MultipartFile, filePath: String):String?{
            val path = Paths.get(filePath).toAbsolutePath().normalize()
            val directory = File(path.toString())
            if(!directory.exists())
            {
                directory.mkdirs()
            }
            val extension = "_"+ file.originalFilename.toString()
            val formatter= SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
            val nameFile= formatter.format(Date().toString().plus(extension))
            try {
                Files.copy(file.inputStream,path.resolve(nameFile), StandardCopyOption.REPLACE_EXISTING)
            }catch (io: IOException)
            {
                println("Hi I'm Exception File"+io)
            }
            return nameFile
        }

        fun storeImage(file: MultipartFile, filePath: String, fileName:String):String?{
            var path= Paths.get(filePath).toAbsolutePath().normalize()
            var directory= File(path.toString())
            if(!directory.exists())
            {
                directory.mkdirs()
            }

            try {
                Files.copy(file.inputStream,path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING)
            }catch (io: IOException)
            {
                println("Hi i'm exception Image file"+io)
            }

            return fileName
        }

        fun removeImage(fileName: String, fileProperty: String): Boolean {
            try {
                val path = Paths.get(fileProperty).toAbsolutePath().normalize()
                val filePath = path.resolve(fileName).normalize()
                val file = File(filePath.toString())

                println("hhhhhhhhhhhh")
                return if (file.exists()) file.delete()
                else false
            } catch (ex: Exception) {
                println(ex)
            }
            println("uiiiiiiiiiiiiii")
            return false

        }


        fun loadFileImage(
            fileName: String,
            fileProperty: String,
            request: HttpServletRequest
        ): ResponseEntity<Resource> {
            try {
                val path = Paths.get(fileProperty).toAbsolutePath().normalize()
                val filePath = path.resolve(fileName).normalize()
                val resource = UrlResource(filePath.toUri())

                resource.contentLength()

                val contentType = request.servletContext.getMimeType(resource.file.absolutePath)
                    ?: throw CustomNotAcceptableException("Invalid file type.")
                return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource)
            } catch (e: MalformedURLException) {
                throw CustomBadRequestException(e.message!!)
            } catch (e: IOException) {
                throw CustomNotFoundException("File not found.")
            }
        }
    }
}