package Stock.smt.model.custom.dto

import Stock.smt.model.*
import java.util.*

data class AdjustmentRequest(
    var id: Int,
    var code: String? = null,
    var adjustmentDate: Date,
//    var adjustmentType: AdjustmentType? =null,
    var create_by: String? = null,
    var description: String? = null,
//    var create_date: LocalDateTime? =null,
    var update_by: String? = null,
//    var update_date: LocalDateTime?=null,
    var adjustmentDetail: MutableList<AdjustmentDetailRequest>? = null
)

data class AdjustmentDetailRequest(
    var id: Int,
    var product: Product? = null,
    var itemVariantUom: ItemVariantUom? =null,
    var employee: Employee? = null,
    var adjustment: Adjustment?= null,
    var qty: Int,
    var reason: String? = null,
    var create_by: String?,
//    var create_date: LocalDateTime?=null,
    var update_by: String?,
//    var update_date: LocalDateTime?=null

)
