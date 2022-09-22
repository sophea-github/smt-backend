package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "supplier")
class Supplier (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var company: String,
    var contact: String,
    var email: String,
    var address: String,
    var description: String,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "supplier")
    var purchaseOrder: MutableList<PurchaseOrder>? = null

)