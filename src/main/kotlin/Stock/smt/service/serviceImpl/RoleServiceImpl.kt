package Stock.smt.service.serviceImpl

import Stock.smt.model.Role
import Stock.smt.repository.RoleRepository
import Stock.smt.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl: RoleService {
    @Autowired
    lateinit var roleRepository: RoleRepository

    override fun findAll(): List<Role>? = roleRepository.findAll()

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun saveAll(t: Role): Role? {
        TODO("Not yet implemented")
    }

    override fun updateObj(id: Int, t: Role): Role? {
        TODO("Not yet implemented")
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<Role> {
        TODO("Not yet implemented")
    }
}