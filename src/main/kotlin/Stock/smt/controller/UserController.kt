package Stock.smt.controller

import Stock.smt.model.custom.dto.LoginDTO
import Stock.smt.model.custom.dto.UserDTO
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.repository.UserRepository
import Stock.smt.repository.UserRoleRepository
import Stock.smt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
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
    lateinit var userRoleRepository: UserRoleRepository

    @PostMapping("/signin")
    fun authentication(@RequestBody loginDto: LoginDTO): MutableMap<String, Any>? {
        return userService.authenticateUser(loginDto)
    }

    @GetMapping("/user")
    fun getUserRole(): MutableMap<String, Any> = responseObjectMap.responseObj(userRoleRepository.findAll())

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/user/{id}")
    fun save(@PathVariable id: Int, @RequestBody t: UserDTO): MutableMap<String, Any> {
        if (!userRepository.existsByEmail(t.email) && !userRepository.existsByContact(t.contact)) {
            return responseObjectMap.responseOBJ(200, userService.register(id, t)!!)
        } else if (userRepository.existsByEmail(t.email) || userRepository.existsByContact(t.contact)) {
            return responseObjectMap.responseOBJ(500, "Checked data duplicate !!")
        } else {
            return responseObjectMap.responseOBJ(400, "Check Field Again")
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/user/{role_id}/{user_id}/{id}")
    fun update(
        @PathVariable role_id: Int,
        @PathVariable user_id: Int,
        @PathVariable id: Int,
        @RequestBody req: UserDTO,
    ): MutableMap<String, Any>? = responseObjectMap.responseObj(userService.update(role_id, user_id, id, req)!!)

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Int): MutableMap<String, Any> = responseObjectMap.responseObj(userService.delete(id))

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/upload/{id}")
    fun uploadImage(@PathVariable id: Int, @RequestParam photo: String): MutableMap<String, Any> =
        responseObjectMap.responseObj(userService.uploadImg(id, photo)!!)

}