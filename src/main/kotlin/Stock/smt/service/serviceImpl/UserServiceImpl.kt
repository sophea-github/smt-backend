package Stock.smt.service.serviceImpl

import Stock.smt.security.JwtTokenProvider
import Stock.smt.config.PasswordConfig
import Stock.smt.model.custom.dto.LoginDTO
import Stock.smt.model.custom.dto.UserDTO
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.User
import Stock.smt.model.UserRole
import Stock.smt.repository.RoleRepository
import Stock.smt.repository.UserRepository
import Stock.smt.repository.UserRoleRepository
import Stock.smt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl: UserService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var userRoleRepository: UserRoleRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @Autowired
    lateinit var passwordConfig: PasswordConfig
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    val MAX_FALED_ATTEMPTS: Int = 3
    val LOCK_TIME_DURATION : Int = (3 * 60 * 1000) //3 minute
    override fun findAll(): List<User>? = userRepository.findAll()
    override fun saveAll(t: User): User? = userRepository.save(t)
    override fun updateObj(id: Int, t: User): User? {
        TODO("Not yet implemented")
    }
    override fun delete(id: Int) {
        try {
            println("id:$id")
            userRoleRepository.deleteById(id)
            userRepository.deleteById(id)
        }catch (e: Exception){
            println("e:$e")
        }
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<User> {
        TODO("Not yet implemented")
    }
    override fun authenticateUser(loginDto: LoginDTO): MutableMap<String,Any> {
        val user = userRepository.getUserLoginByUsername(loginDto.username)
        val passEncoder: PasswordEncoder = BCryptPasswordEncoder()
        if (user != null) {
            if (user.accountNonLocked) {
                if (user.failedAttempt!! < MAX_FALED_ATTEMPTS - 1) {
                    if (passEncoder.matches(loginDto.password, user.password) && user.username == loginDto.username) {
                        val authentication = authenticationManager.authenticate(
                            UsernamePasswordAuthenticationToken(
                                loginDto.username,
                                loginDto.password
                            )
                        )
                        SecurityContextHolder.getContext().authentication = authentication
                        val token: String? = jwtTokenProvider.generateToken(authentication!!)
                        resetFailedAttempt(loginDto.username)
                        return responseObjectMap.responseOBJ(200,token.toString())
                    }
                    else {
                        increaseFailedAttempt(user)
                        return responseObjectMap.responseOBJ(401,"Check username and password again !!")
                    }
                } else {
                    lock(user)
                    return responseObjectMap.responseOBJ( 403,
                        "Your Account has been locked due to 3 failed attempts It will be unlocked after 3 minute"
                    )
                }
            } else if (!user.accountNonLocked) {
                if (unlockUser(user)) {
                    return responseObjectMap.responseOBJ( 406,
                        "Your account has been unlocked please try login again"
                    )
                }
            }
        }else{
            return responseObjectMap.responseOBJ(404,"login failed")
        }
        return responseObjectMap.responseOBJ( 402,
            "Your Account has been locked It will be unlocked after 3 minute"
        )
    }
    override fun register(id: Int,req: UserDTO): MutableMap<String, Any>? {
        val role = roleRepository.findByIdAndStatusIsTrue(id)
        synchronized(this){
            try {
                if(!userRepository.existsByEmail(req.email) && !userRepository.existsByContact(req.contact)){
                    val user = userRepository.save(
                        User(
                            id = 0,
                            username= req.username,
                            gender = req.gender,
                            dob = req.dob,
                            password = passwordConfig.passwordEndCode().encode(req.password),
                            contact = req.contact,
                            email = req.email,
                            address = req.address,
                            photo = req.photo
                        )
                    )
                    userRoleRepository.save(
                        UserRole(
                            id = 0,
                            role =  role,
                            user = user,
                        )
                    )
                }
            }catch (e: Exception){}
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }
    override fun update(role_id: Int, user_id: Int, id: Int, req: UserDTO): MutableMap<String, Any>? {
        val user = userRepository.findByIdAndStatusIsTrue(user_id)
        val role = roleRepository.findByIdAndStatusIsTrue(role_id)
        val userRole = userRoleRepository.findByIdAndStatusIsTrue(id)
        synchronized(this){
            try {
                    user?.username = req.username
                    user?.gender = req.gender
                    user?.dob = req.dob
                    user?.password = passwordConfig.passwordEndCode().encode(req.password)
                    user?.contact = req.contact
                    user?.email = req.email
                    user?.address = req.address
                    user?.photo = req.photo
                    userRepository.save(user!!)
                    userRole?.user = user
                    userRole?.role = role
                userRoleRepository.save(userRole!!)

            }catch (e: Exception){ }
        }
        return responseObjectMap.responseOBJ(200, "Success!!")
    }
    override fun uploadImg(id: Int, photo: String): User? {
        val user = userRepository.findByIdAndStatusIsTrue(id)
        user?.photo = photo
        return userRepository.save(user!!)
    }
    override fun increaseFailedAttempt(user: User) {
        val newFailedAttempts: Int = user.failedAttempt!! + 1
        userRepository.updateFailedAttempt(newFailedAttempts, user.username)
    }
    override fun resetFailedAttempt(username: String?) {
        userRepository.updateFailedAttempt(0, username)
    }
    override fun lock(user: User) {
        user.accountNonLocked= false
        user.lockTime = Date()
        userRepository.save(user)
    }
    override fun unlockUser(user: User): Boolean {
        val lockTimeInMillis: Long = user.lockTime!!.time
        val currentTimeInMillis = System.currentTimeMillis()
        if (lockTimeInMillis+ LOCK_TIME_DURATION < currentTimeInMillis) {
            user.accountNonLocked= true
            user.lockTime = null
            user.failedAttempt = 0
            userRepository.save(user)
            return true
        }
        return false
    }

}