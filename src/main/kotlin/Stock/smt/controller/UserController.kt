package Stock.smt.controller

import Stock.smt.model.Custom.DTO.LoginDTO
import Stock.smt.model.Custom.DTO.UserDTO
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.User
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

    @GetMapping
    fun authentication(@RequestBody loginDto: LoginDTO): MutableMap<String, Any>? {
        return userService.authenticateUser(loginDto)
    }
    @GetMapping("/user")
    fun getData(): MutableMap<String,Any> = responseObjectMap.ResponseObj(userService.findAll()!!)
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/{id}")
    fun save(@PathVariable id: Int , @RequestBody t: UserDTO): MutableMap<String,Any> = userService.register(id,t)!!
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/{role_id}/{id}")
    fun update(@PathVariable role_id: Int,@PathVariable id: Int,@RequestBody req: UserDTO): MutableMap<String, Any>? = responseObjectMap.ResponseObj(userService.update(role_id,id,req)!!)
    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String,Any> = responseObjectMap.ResponseObj(userService.delete(id)!!)
    @PostMapping("/user/upload/{id}")
    fun uploadImage(@PathVariable id: Int,@RequestParam photo: String): MutableMap<String,Any> = responseObjectMap.ResponseObj(userService.uploadImg(id,photo)!!)


}