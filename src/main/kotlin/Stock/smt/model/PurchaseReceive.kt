package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "purchase_receive")
class PurchaseReceive(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var code: String,

    @DateTimeFormat(style = "dd/MM/yyyy")
    var receive_date: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_by")
    var employee: Employee? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier? = null,

    @DateTimeFormat(style = "dd/MM/yyyy")
    var create_date: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency")
    var changeRate: ChangeRate?= null,

    var create_by: String,

    var description: String,

    var status : Boolean= true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    var purchase_order: PurchaseOrder? = null,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseReceive")
    var purchaseReceiveDetail: MutableList<PurchaseReceiveDetail>? = null

)