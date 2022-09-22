package Stock.smt.model.Custom.DTO

import Stock.smt.model.Employee
import Stock.smt.model.ItemVariantUom
import Stock.smt.model.Product
import java.util.*

interface PurchaseOrderDetailDTO {
    var id: Int?
    var code: String?
    var product: Product?
    var employee: Employee?
    var order_date: Date?
    var create_by: String?
    var create_date: Date?
    var status:Boolean?
    var itemVariantUom: ItemVariantUom?
}