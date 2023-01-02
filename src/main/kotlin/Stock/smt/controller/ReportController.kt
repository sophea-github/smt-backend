package Stock.smt.controller

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/")
class ReportController {
    @Autowired
    lateinit var reportService: ReportService

    @Autowired
    lateinit var response: ResponseObjectMap

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("reportProduct")
    fun generateProductReport(): String {
        return reportService.generateReportProduct()
    }

    @PreAuthorize("hasAnyRole('ADMIN','PURCHASE')")
    @GetMapping("reportPurchase/{startDate}/{endDate}")
    fun generatePurchaseReport(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") startDate: Date,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") endDate: Date,
    ) {
        reportService.generateReportPurchase(startDate, endDate)
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("reportAdjustment/{adjustment}/{startDate}/{endDate}")
    fun generateAdjustmentReport(
        @PathVariable adjustment: String,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") startDate: Date,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") endDate: Date,
    ) {
        reportService.generateReportAdjustment(adjustment, startDate, endDate)
    }
}