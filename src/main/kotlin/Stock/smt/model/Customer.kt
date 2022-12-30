package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="customer")
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var company: String? =null,
    var name: String?,
    var contact: String,
    var email: String? = null,
    var address: String,
    var description: String? = null,
    var create_by: String,
    var create_date: LocalDateTime,
    var status: Boolean= true,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    var sale: MutableList<Sale>? = null
)