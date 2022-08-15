package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
@Entity
@Table(name="product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    @Column(nullable = false,unique = true)
    var code: String,
    @Column(unique = true)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    var category: Category? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_variant_id")
    var itemVariantUom: ItemVariantUom? = null,

    var qty: Int = 0,
    var amt: Int= 0,
    var price: Float,
    var photo: String? = null,
    var active: String? = null,
    var stock_alert: String? =null,
    var create_by: String? = null,
    var description: String,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    var purchaseOrderDetail: MutableList<Purchase_Order_Detail>? = null
)