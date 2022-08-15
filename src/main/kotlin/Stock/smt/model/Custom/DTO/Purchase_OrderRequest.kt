package Stock.smt.model.Custom.DTO

import java.util.*

class Purchase_OrderRequest (
    var id: Int= 0,
    var qty: Int ,
    var price: Double,
    var order_date: Date,
    var active: String? = null,
    var create_by: String? = null,
    var description: String,
    var status :  Boolean = true
    )
