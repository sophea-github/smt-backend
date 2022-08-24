package Stock.smt.repository


import Stock.smt.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository:JpaRepository<Role,Int> {
    fun findByIdAndStatusIsTrue(id: Int): Role?

//    fun
}