package Stock.smt.model.custom.dto

import Stock.smt.model.*
import java.util.*


interface PoDTO {
    var id: Int
    var employee: Employee?
    var supplier: Supplier?
    var code: String?
    var order_date: Date
    var create_by: String?
    var create_date: Date?
    var description: String
    var status: Boolean
    var changeRate: ChangeRate?
    var purchaseOrderDetail: MutableList<PurchaseOrderDetail>?
}