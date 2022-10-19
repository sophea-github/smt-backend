package Stock.smt.model.Custom.DTO

import org.apache.poi.hpsf.Date

interface AdDTO {
    var id: Int?
    var code: String?
    var adjustmentDate: String?
    var type: String?
    var description: String?
    var total: Int?

}