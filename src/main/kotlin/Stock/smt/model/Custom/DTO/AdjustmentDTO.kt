package Stock.smt.model.Custom.DTO
import Stock.smt.model.AdjustmentDetail
import Stock.smt.model.AdjustmentType
import java.util.*
interface AdjustmentDTO {
    var id: Int
    var code: String
    var adjustmentDate: Date
    var adjustmentType: AdjustmentType?
    var adjustmentDetail: MutableList<AdjustmentDetail>
}