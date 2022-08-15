package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.SendMailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.task.TaskExecutor
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.mail.internet.InternetAddress

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class SendMailController constructor(
    private val response: ResponseObjectMap,
    private val sendMailService: SendMailService,
    private val taskExecutor: TaskExecutor
) {

    @GetMapping("/mail")
    fun sendEmailtoEmail (@RequestParam toEmail: Array<InternetAddress>, @RequestParam subject: String, @RequestParam message: String): MutableMap<String, Any> {
        taskExecutor.execute {
            sendMailService.sendMailToUser(toEmail, subject, message)
        }
        return response.responseOBJ(200, "Success")
    }

    @GetMapping("/file")
    fun sendEmailWithAttachment(@RequestParam toEmail: Array<InternetAddress>, subject: String, message: String, file: MultipartFile): MutableMap<String, Any> {
      try {
          taskExecutor.execute {
              sendMailService.sendEmailWithAttachment(toEmail, subject, message, file)
          }
      }catch (e:Exception){}
        return response.responseOBJ(200, "Success!!! test")
    }



}