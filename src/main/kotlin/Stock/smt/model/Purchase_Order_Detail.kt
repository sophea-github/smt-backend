package Stock.smt.model

import javax.persistence.*

@Entity
@Table(name="purchase_order_detail")
class Purchase_Order_Detail (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    var product: Product? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_size")
    var itemVariantUom: ItemVariantUom? = null,
    var qty: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchase_order_id")
    var purchaseOrder: Purchase_Order? = null,
    var price: Double?= null,
    var status: Boolean = true

)