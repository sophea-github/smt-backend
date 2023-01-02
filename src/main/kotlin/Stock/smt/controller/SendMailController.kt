package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.SendMailService
import org.springframework.core.task.TaskExecutor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.mail.internet.InternetAddress

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1")
@CrossOrigin
class SendMailController constructor(
    private val response: ResponseObjectMap,
    private val sendMailService: SendMailService,
    private val taskExecutor: TaskExecutor,
) {
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/mail")
    fun sendEmailToEmail(
        @RequestParam toEmail: Array<InternetAddress>,
        @RequestParam subject: String,
        @RequestParam message: String,
    ): MutableMap<String, Any> {
        taskExecutor.execute {
            sendMailService.sendMailToUser(toEmail, subject, message)
        }
        return response.responseOBJ(200, "Success")
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/file")
    fun sendEmailWithAttachment(
        @RequestParam toEmail: Array<InternetAddress>,
        subject: String,
        message: String,
        file: MultipartFile?,
    ): MutableMap<String, Any> {
//      try {
        taskExecutor.execute {
            sendMailService.sendEmailWithAttachment(toEmail, subject, message, file)
        }
//      }catch (e:Exception){}
        return response.responseOBJ(200, "Success!!")
    }

}