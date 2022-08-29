package Stock.smt.controller

import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class RoleController {
    @Autowired
    lateinit var roleService: RoleService
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/role")
    fun getAll(): MutableMap<String,Any> = responseObjectMap.ResponseObj(roleService.findAll()!!)
}