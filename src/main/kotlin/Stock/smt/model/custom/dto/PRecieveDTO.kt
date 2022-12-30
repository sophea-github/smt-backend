package Stock.smt.model.custom.dto

interface PRecieveDTO {
    val code: String
    val name: String
    val product: String
    val item_variant_name: String
    val qty: Int
    val price: Double
    val symbol: String
    val subtotal: Double
}