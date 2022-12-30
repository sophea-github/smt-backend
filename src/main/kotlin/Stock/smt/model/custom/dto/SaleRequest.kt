package Stock.smt.model.custom.dto

import Stock.smt.model.Product
import java.util.*

data class SaleRequest (
    val id: Int?,
    val saleDate: Date,
    val description: String,
    val create_by: String,
    val update_by: String,
    val saleDetail: MutableList<SaleDetailRequest>? = null
)

data class SaleDetailRequest(
    val id: Int,
    val product: Product? = null,
    val qty: Int,
    val unit_price: Float,
    val create_by: String,
)