package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.custom.dto.SaleRequest
import Stock.smt.model.Sale

interface SaleService: BaseFun<Sale, Int> {

    fun getSale(): MutableList<Sale>
    fun newSale(empId: Int, cusId: Int, t: SaleRequest): MutableMap<String,Any>
    fun updateSale(empId: Int, cusId: Int,id: Int, t: SaleRequest): MutableMap<String, Any>
    fun deleteSale(id: Int): MutableMap<String,Any>

}