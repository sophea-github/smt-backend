package Stock.smt.model.custom.dto

import Stock.smt.model.ChangeRate
import Stock.smt.model.Employee
import java.util.*

data class PurchaseOrderRequest (
    var id: Int= 0,
//    var supplier: Supplier? =null,
    var code: String? = null,
    var order_date: Date,
    var create_by: String? = null,
    var description: String,
    var purchaseOrderDetail: MutableList<PoDetailRequest>? = null,
    var status:Boolean = true,
    var changeRate: ChangeRate? = null,
    var employee: Employee? = null,
//    var purchaseOrderDetail: MutableList<PurchaseOrderDetail>? = null
)
data class PoDetailRequest(
    var poid: Int?,
    var qty: Long,
    var price: Double,
    var product: ProductRequest?,
    var create_by: String?
)
data class ProductRequest(
    var id: Int = 0,
    var code: String? = null,
    var name: String? = null,
    var qty: Int = 0,
    var photo: String? = null,
    var price: Double? = 0.0,
    var amt: Int= 0,
    var active: String? = null,
    var stock_alert: String? =null,
    var description: String? = null,
)

