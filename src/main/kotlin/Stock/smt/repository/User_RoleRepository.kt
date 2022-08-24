package Stock.smt.repository

import Stock.smt.model.User_Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface User_RoleRepository: JpaRepository<User_Role,Int> {
    fun findByIdAndStatusIsTrue(id: Int): User_Role?
}