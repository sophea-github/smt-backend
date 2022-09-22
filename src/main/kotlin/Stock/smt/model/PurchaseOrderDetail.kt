package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="purchase_order_detail")
class PurchaseOrderDetail(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    var product: Product? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_size")
    var itemVariantUom: ItemVariantUom,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchase_order_id")
    var purchaseOrder: PurchaseOrder? = null,
    var qty: Int,
    var price: Double?= null,
    var create_by: String? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var create_date: LocalDateTime,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "purchaseOrderDetail")
    var purchaseReceiveDetail:MutableList<PurchaseReceiveDetail>? = null

)