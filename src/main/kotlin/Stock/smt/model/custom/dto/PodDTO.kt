package Stock.smt.model.custom.dto

import Stock.smt.model.Product
import Stock.smt.model.PurchaseOrder
import Stock.smt.model.PurchaseReceiveDetail
import java.time.LocalDateTime

interface PodDTO {

    var id: Int?
    var product: Product?
//    var itemVariantUom: ItemVariantUom?
    var purchaseOrder: PurchaseOrder?
    var qty: Int?
    var price: Double?
    var create_by: String?
    var create_date: LocalDateTime?
    var purchaseReceiveDetail:MutableList<PurchaseReceiveDetail>?



}