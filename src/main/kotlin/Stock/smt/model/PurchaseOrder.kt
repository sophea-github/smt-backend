package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="purchase_order")
class PurchaseOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var code: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    var employee: Employee? = null,

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var order_date: Date,
    var create_by: String? = null,

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var create_date: LocalDateTime,
    var description: String? = null,
    var status:Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    var changeRate: ChangeRate? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier? = null,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "purchaseOrder")
    var purchaseOrderDetail: MutableList<PurchaseOrderDetail>? = null,

    )