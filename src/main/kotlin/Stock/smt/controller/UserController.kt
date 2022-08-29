package Stock.smt.controller

import Stock.smt.model.Custom.DTO.LoginDTO
import Stock.smt.model.Custom.DTO.UserDTO
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.User
import Stock.smt.repository.UserRepository
import Stock.smt.repository.User_RoleRepository
import Stock.smt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
class UserController {
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var userRolerepository: User_RoleRepository

    @PostMapping("/signin")
    fun authentication(@RequestBody loginDto: LoginDTO): MutableMap<String, Any>? {
        return userService.authenticateUser(loginDto)
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @GetMapping("/user")
    fun getUserRole(): MutableMap<String,Any> = responseObjectMap.ResponseObj(userRolerepository.findAll()!!)

    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @PostMapping("/user/{id}")
    fun save(@PathVariable id: Int , @RequestBody t: UserDTO): MutableMap<String,Any> {
            if (!userRepository.existsByEmail(t.email) && !userRepository.existsByContact(t.contact)){
                return responseObjectMap.responseOBJ(200,userService.register(id,t)!!)
            }else if(userRepository.existsByEmail(t.email) || userRepository.existsByContact(t.contact)) {
                return responseObjectMap.responseOBJ(500,"Checked data duplicate !!")
            }else{
                return responseObjectMap.responseOBJ(400,"Check Field Again")
            }
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @PutMapping("/user/{role_id}/{id}")
    fun update(@PathVariable role_id: Int,@PathVariable id: Int,@RequestBody req: UserDTO): MutableMap<String, Any>? = responseObjectMap.ResponseObj(userService.update(role_id,id,req)!!)

    @PreAuthorize("hasAnyRole('ADMIN','USER','PURCHASE','SALE')")
    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(userService.delete(id)!!)

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/upload/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.ResponseObj(userService.uploadImg(id,photo)!!)

}