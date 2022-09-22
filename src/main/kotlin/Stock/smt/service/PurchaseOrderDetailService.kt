package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Custom.DTO.PurchaseOrderDetailDTO
import Stock.smt.model.PurchaseOrderDetail

interface PurchaseOrderDetailService: BaseFun<PurchaseOrderDetail,Int> {
    fun findByIDPOD(poId: Int):List<PurchaseOrderDetailDTO>?
}