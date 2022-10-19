package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Custom.DTO.PurchaseOrderDTO
import Stock.smt.model.Custom.DTO.PurchaseOrderRequest
import Stock.smt.model.Custom.DTO.PoDTO
import Stock.smt.model.PurchaseOrder
import org.springframework.data.jpa.repository.Query

interface PurchaseOrderService : BaseFun<PurchaseOrder, Int> {
    //select count join
    fun findPo(id: Int): PoDTO?
    fun findAllPO(): List<PurchaseOrderDTO>
    fun addPO(cr_id: Int,emp_id: Int,supId: Int,req: PurchaseOrderRequest): MutableMap<String, Any>?
    fun updatePO(emp_id: Int, currency_id: Int,supId: Int,po_id: Int, req: PurchaseOrderRequest) : MutableMap<String, Any>?
    fun deletePO(id: Int): MutableMap<String,Any>?
}