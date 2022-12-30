package Stock.smt.model

import java.time.LocalDateTime
import javax.persistence.*
@Entity
@Table(name = "SaleDetail")
class SaleDetail (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var itemVariantUom: ItemVariantUom? =null,
    var qty: Int,
    var unit_price: Float,
    var create_by: String,
    var create_date: LocalDateTime,
    var status: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    var sale: Sale? =null
)