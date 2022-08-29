package Stock.smt.repository

import Stock.smt.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findByIdAndStatusIsTrue(id: Int): User?
    fun findByUsernameOrEmail(username: String,Email: String): User
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun existsByContact(contact: String): Boolean

    @Query("SELECT u FROM User u WHERE u.username= :username")
    @Transactional
    fun getUserLoginByUsername(@Param("username") username: String?): User?

    @Query("UPDATE User u SET u.failedAttempt= ?1 WHERE u.username= ?2")
    @Modifying
    @Transactional
    fun updateFailedAttempt(failedAttempt: Int, username: String?)
}