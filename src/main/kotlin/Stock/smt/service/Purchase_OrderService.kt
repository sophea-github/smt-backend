package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Custom.DTO.Purchase_OrderRequest
import Stock.smt.model.Purchase_Order

interface Purchase_OrderService : BaseFun<Purchase_Order, Int> {
    fun addPO(emp_id: Int,pro_id: Int,item_id: Int,req: Purchase_OrderRequest): MutableMap<String, Any>?
    fun updatePO(emp_id: Int,pro_id: Int,item_id: Int,id: Int,req: Purchase_OrderRequest) : MutableMap<String, Any>?
}