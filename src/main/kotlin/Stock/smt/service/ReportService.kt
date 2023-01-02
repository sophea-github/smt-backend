package Stock.smt.service

import java.util.*

interface ReportService {
    fun generateReportProduct(): String
    fun generateReportPurchase(startDate: Date, endDate: Date): MutableMap<String, Any>
    fun generateReportAdjustment(adjustment: String, startDate: Date, endDate: Date): MutableMap<String, Any>

}