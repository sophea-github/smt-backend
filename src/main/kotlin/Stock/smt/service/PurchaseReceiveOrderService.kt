package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Custom.DTO.PorDTO
import Stock.smt.model.Custom.DTO.PurchaseReceiveDTO
import Stock.smt.model.Custom.DTO.PurchaseReceiveOrderRequest
import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.model.PurchaseReceive

interface PurchaseReceiveOrderService: BaseFun<PurchaseReceive, Int> {

    fun addPurchaseReceive(empId: Int,req: PurchaseReceiveOrderRequest): MutableMap<String,Any>
    fun updatePurchaseReceive(empId: Int, crId: Int, porId: Int , req: PurchaseReceiveOrderRequest): MutableMap<String,Any>
    fun findAllPor(): List<PurchaseReceiveDTO>
    fun findProOrderDetail(proId: Int,code: String): PurchaseOrderDetail?
    fun findPorDetail(id: Int): PorDTO
    fun deletePor(id: Int): MutableMap<String,Any>
}