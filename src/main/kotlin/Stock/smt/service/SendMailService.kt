package Stock.smt.service
import org.springframework.web.multipart.MultipartFile
import javax.mail.internet.InternetAddress

interface SendMailService {

    fun sendMailToUser(tomail: Array<InternetAddress> ,subject: String, msg: String) :MutableMap<String, Any>
    fun sendEmailWithAttachment(toEmail: Array<InternetAddress>, subject: String,message: String, file: MultipartFile?): MutableMap<String, Any>




}