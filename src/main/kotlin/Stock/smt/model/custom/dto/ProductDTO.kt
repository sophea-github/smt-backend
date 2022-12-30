package Stock.smt.model.custom.dto

interface ProductDTO {
    var id: Int
    var code: String?
    var category: String?
    var product: String?
    var uom: String?
    var quantity: Int?
    var price: Float?
    var status: String?
}