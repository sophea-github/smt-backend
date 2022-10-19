package Stock.smt.model.Custom.DTO

import Stock.smt.model.*
import java.util.*

interface PorDTO {
    var id: Int
    var employee: Employee?
    var supplier: Supplier?
    var code: String?
//    var order_date: Date?
    var purchase_order: PurchaseOrder?
    var receive_date: Date?
    var create_by: String?
    var create_date: Date?
    var description: String?
    var status: Boolean?
    var changeRate: ChangeRate?
    var purchaseReceiveDetail: MutableList<PurchaseReceiveDetail>?
}