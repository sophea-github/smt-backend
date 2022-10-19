package Stock.smt.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "adjustmentDetail")
class AdjustmentDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var itemVariantUom: ItemVariantUom? =null,
    var qty: Int,
    var reason: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var adjustment: Adjustment?= null,
    @ManyToOne(fetch = FetchType.LAZY)
    var employee: Employee? = null,
    var create_by: String? = null,
    var create_date: LocalDateTime?=null,
    var update_by: String? = null,
    var update_date: LocalDateTime?=null,
    var status: Boolean =true,
)