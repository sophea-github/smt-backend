package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.custom.dto.PorDTO
import Stock.smt.model.custom.dto.PurchaseReceiveDTO
import Stock.smt.model.custom.dto.PurchaseReceiveOrderRequest
import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.model.PurchaseReceive
import java.util.*

interface PurchaseReceiveOrderService: BaseFun<PurchaseReceive, Int> {

    fun addPurchaseReceive(empId: Int,req: PurchaseReceiveOrderRequest): MutableMap<String,Any>
    fun updatePurchaseReceive(empId: Int, crId: Int, porId: Int , req: PurchaseReceiveOrderRequest): MutableMap<String,Any>
    fun findAllPor(): List<PurchaseReceiveDTO>
    fun findProOrderDetail(proId: Int,code: String): PurchaseOrderDetail?
    fun findPorDetail(id: Int): PorDTO
    fun deletePor(id: Int): MutableMap<String,Any>
//    fun reportPurchase(startDate: Date, endDate: Date):
}