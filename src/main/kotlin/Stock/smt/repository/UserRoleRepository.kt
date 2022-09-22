package Stock.smt.repository

import Stock.smt.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository: JpaRepository<UserRole,Int> {
    fun findByIdAndStatusIsTrue(id: Int): UserRole?
}