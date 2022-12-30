package Stock.smt.model.custom.dto

import Stock.smt.model.UserRole
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

class UserDTO(

    var id: Int,
    var username: String,
    var gender: String,
    var contact: String,
    var email: String,
    var password: String,
    @DateTimeFormat(style = "dd/MM/yyyy")
    var dob: Date,
    var address: String,
    var photo: String?= null,
    var userRole: UserRole? = null
)