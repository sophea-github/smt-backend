package Stock.smt.model.Custom.DTO

import Stock.smt.model.*
import java.util.*

data class PurchaseReceiveOrderRequest(
    var id: Int,
    var receive_date: Date,
    val changeRate: ChangeRate? = null,
    var employee: Employee? = null,
    var create_by: String?,
    var description: String?,
    var status : Boolean= true,
    var purchase_order: PurchaseOrder? = null,
    var purchaseReceiveDetail: MutableList<PurchaseReceiveDetailRequest>? = null
)
data class PurchaseReceiveDetailRequest(
        var id: Int,
        var qty: Int,
        var product: Product? = null,
        var create_by: String? = null,
        var description: String? = null,
        var status: Boolean = true,
//        var purchaseOrderDetail: PurchaseOrderDetail? = null
)