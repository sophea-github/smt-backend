package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "change_rate")
class ChangeRate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var type: String,
    var rate: Float,
    var symbol: String,
    var description: String,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "changeRate")
    var purchaseOrder: MutableList<PurchaseOrder>? =null,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "changeRate")
    var purchaseReceive: MutableList<PurchaseReceive>? =null
)
