package Stock.smt.model.custom.dto

import Stock.smt.model.Customer
import Stock.smt.model.Employee
import java.util.*

interface SaleDTO {

    val id: Int?
    val employee: Employee?
    val customer: Customer?
    val saleDate: Date?
}