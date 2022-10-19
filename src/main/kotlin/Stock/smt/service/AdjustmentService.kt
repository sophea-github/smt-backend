package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Adjustment
import Stock.smt.model.Custom.DTO.AdDTO
import Stock.smt.model.Custom.DTO.AdjustmentDTO
import Stock.smt.model.Custom.DTO.AdjustmentRequest

interface AdjustmentService: BaseFun<Adjustment, Int> {

    fun findAdjustment(id: Int): AdjustmentDTO?
    fun findCountAdjustment(): List<AdDTO>
    fun addAdjustment(adjustmentId: Int , req: AdjustmentRequest): MutableMap<String,Any>?
    fun updateAdjustment(adjustmentId: Int,id: Int , req: AdjustmentRequest): MutableMap<String,Any>?
    fun deleteAdjustment(id: Int): MutableMap<String,Any>?
}