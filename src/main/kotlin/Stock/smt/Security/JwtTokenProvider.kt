package Stock.smt.Security

import Stock.smt.model.Custom.Exception.BlogAPIException
import org.springframework.beans.factory.annotation.Value
import java.security.SignatureException
import java.util.*
import io.jsonwebtoken.*
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider {
    @Value("\${app.jwt-secret}")
    private val jwtSecret: String? = null
    @Value("\${app.jwt-expiration-milliseconds}")
    private val jwtExpirationInMs = 0

    // generate token
    fun generateToken(authentication: org.springframework.security.core.Authentication): String? {
        val username: String = authentication.name
        val currentDate = Date()
        val expireDate = Date(currentDate.getTime() + jwtExpirationInMs)
        println("expire token: "+ expireDate)
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(expireDate)
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    // get username from the token
    fun getUsernameFromJWT(token: String?): String? {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    // validate JWT token
    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (ex: SignatureException) {
            throw BlogAPIException("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            throw BlogAPIException("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            throw BlogAPIException("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            throw BlogAPIException("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            throw BlogAPIException("JWT claims string is empty.")
        }
    }
}