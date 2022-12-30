package Stock.smt.service

import Stock.smt.model.custom.dto.PRecieveDTO
import java.util.*

interface ReportService {
    fun generateReportProduct(): String
    fun generateReportPurchase(startDate: Date, endDate: Date): String
    fun generateReportAdjustment(adjustment: String,startDate: Date, endDate: Date): String

}