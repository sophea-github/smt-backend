package Stock.smt.model.Custom.DTO

import java.util.*


interface PurchaseOrderDTO {
    var id: Int?
    var code: String?
    var supplier_id: Int?
    var supplier_company: String?
    var supplier_address: String?
    var supplier_contact: String?
    var supplier_email: String?
    var employee_id: Int?
    var employeeName: String?
    var photo: String?
    var changeRate_Id: Int?
    var changeRateSymbol: String?
    var order_date: Date?
    var create_by: String?
    var create_date: Date?
    var description: String?
    var totalPrice: Double?
    var totalItem: Int?
//    var purchaseOrderDetail: List<PurchaseOrderDetail>
}