package Stock.smt.model.Custom.DTO

import java.util.*

interface PurchaseReceiveDTO {
    var id: Int?
    var code: String?
    var supplier_id: Int?
    var supplier_company: String?
    //receive by
    var employee_id: Int?
    var employeeName: String?
    var photo: String?
    //currency
    var changeRate_Id: Int?
    var changeRateSymbol: String?
    var order_date: Date?
    var receive_date: Date?
    var create_by: String?
    var create_date: Date?
    var description: String?
    var totalPrice: Double?
    var totalItem: Int?
    var order_by : String?
    var order_empPhoto : String?
}