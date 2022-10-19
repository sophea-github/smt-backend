package Stock.smt.model
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import  java.util.*

@Entity
@Table(name = "users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var username: String,
    var gender: String,
    var contact: String,
    @Column(unique = true)
    var email: String,
    var password: String,
    @JsonFormat(pattern="yyyy-MM-dd")
    var dob: Date,
    var address: String,
    var photo: String? = null,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    var userRole: MutableList<UserRole>? = null,

    @Column(name = "account_non_locked")
    var accountNonLocked: Boolean = true,

    @Column(name = "failed_attempt")
    var failedAttempt: Int? = 0,

    @Column(name = "lock_time")
    var lockTime: Date?= null


)