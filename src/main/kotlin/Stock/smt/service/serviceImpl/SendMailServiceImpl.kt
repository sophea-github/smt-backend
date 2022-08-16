package Stock.smt.service.serviceImpl

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.SendMailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.mail.internet.InternetAddress
import javax.mail.util.ByteArrayDataSource

@Service
class SendMailServiceImpl : SendMailService {
    @Autowired
    lateinit var response: ResponseObjectMap
    //    @Autowired
//    lateinit var userController: UserController
    @Autowired
    lateinit var mailSender: JavaMailSender

    @Value( "Stock Company")
    private val mailSenderName: String? = null

    @Value("\${spring.mail.username}")
    private val emailAddress: String? = null
    @Throws(Exception::class)
    override fun sendMailToUser(tomail: Array<InternetAddress>, subject: String, msg: String): MutableMap<String, Any> {
        val msger = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(msger, true)
        helper.setFrom(InternetAddress("$mailSenderName ${"<"} $emailAddress ${">"}"))
        helper.setTo(tomail)
        helper.setSubject(subject)
        helper.setText(msg, true)
        mailSender.send(msger)
        return response.responseOBJ(200, "Mail was sent successfully to $tomail")
    }

    override fun sendEmailWithAttachment(
        toEmail: Array<InternetAddress>,
        subject: String,
        message: String,
        file: MultipartFile?
    ): MutableMap<String, Any> {
        synchronized(this) {
            val msg = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(msg, true)
            helper.setFrom(InternetAddress("$mailSenderName ${"<"} $emailAddress ${">"}"))
            helper.setBcc(toEmail)
            helper.setSubject(subject)
            helper.setText(message,true)
            val attachment = ByteArrayDataSource(file?.inputStream, "application/octet-stream")
            helper.addAttachment(file?.originalFilename!!, attachment)
            mailSender.send(msg)
            return response.responseOBJ(200, "Success!!!!!!!!!")
        }
    }
}