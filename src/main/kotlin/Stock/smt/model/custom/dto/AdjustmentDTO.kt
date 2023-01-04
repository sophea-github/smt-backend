package Stock.smt.model.custom.dto
import Stock.smt.model.AdjustmentDetail
import Stock.smt.model.AdjustmentType
import java.util.*
interface AdjustmentDTO {
    var id: Int
    var code: String
    var adjustmentDate: Date
    var adjustmentType: AdjustmentType?
    var description: String
    var adjustmentDetail: MutableList<AdjustmentDetail>
}