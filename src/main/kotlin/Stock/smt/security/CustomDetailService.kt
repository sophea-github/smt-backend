package Stock.smt.security

import Stock.smt.model.custom.exception.CustomNotFoundException
import Stock.smt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional
class CustomDetailService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?: throw CustomNotFoundException("Username Not Found !!")
        return org.springframework.security.core.userdetails.User(
            user.username, user.password,user.userRole?.stream()?.map { userRole-> SimpleGrantedAuthority(userRole.role?.name.toString()) }?.collect(
                Collectors.toList())
        )
    }
}