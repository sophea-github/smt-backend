package Stock.smt.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "purchase_receive_detail")
class PurchaseReceiveDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var qty: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null,
    var create_by: String? = null,
    var create_date: LocalDateTime,
    var description: String,
    var status: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseReceive_id")
    var purchaseReceive: PurchaseReceive? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchaseOrderDetail_id")
    var purchaseOrderDetail: PurchaseOrderDetail? = null

)