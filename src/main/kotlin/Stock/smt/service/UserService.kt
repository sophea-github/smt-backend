package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.custom.dto.LoginDTO
import Stock.smt.model.custom.dto.UserDTO
import Stock.smt.model.User

interface UserService: BaseFun<User,Int> {
    fun authenticateUser(loginDto: LoginDTO): MutableMap<String,Any>?
    fun register(id: Int , req: UserDTO): MutableMap<String, Any>?
    fun update( role_id: Int ,user_id: Int, id: Int , req: UserDTO): MutableMap<String, Any>?
    fun uploadImg(id: Int,photo: String): User?
    fun increaseFailedAttempt(user: User)
    fun resetFailedAttempt(username: String?)
    fun lock(user: User)
    fun unlockUser(user: User): Boolean


}