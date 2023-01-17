package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.custom.dto.PRecieveDTO
import Stock.smt.model.custom.dto.ProductDTO
import Stock.smt.model.custom.dto.ReportAdjDto
import Stock.smt.model.custom.exception.CustomException
import Stock.smt.model.custom.exception.CustomNotFoundException
import Stock.smt.repository.AdjustmentRepository
import Stock.smt.repository.ProductRepository
import Stock.smt.repository.PurchaseReceiveOrderRepository
import Stock.smt.service.ReportService
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.File
import java.util.*
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap


@Service
class ReportServiceImpl : ReportService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var purchaseReceiveOrderRepository: PurchaseReceiveOrderRepository

    @Autowired
    lateinit var adjustmentRepository: AdjustmentRepository

    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    @Autowired
    lateinit var response: HttpServletResponse

    override fun generateReportProduct(): String {
        val part: String = "E:\\thesis"
        val p: List<ProductDTO> = productRepository.reportProduct()
        val file: File = ResourceUtils.getFile("classpath:ReportProduct.jrxml")
        val jasperReport = JasperCompileManager.compileReport(file.absolutePath)
        val dataSource = JRBeanCollectionDataSource(p)
        val parameters: MutableMap<String, Any> = HashMap()
        parameters["createdBy"] = "Java"
//        println("para:"+ parameters)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource)
        /**
         * export to pdf
         *
         */
//        JasperExportManager.exportReportToPdfFile(jasperPrint, part+"productReport.pdf");
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, part+"productReport.pdf")
//            JasperExportManager.exportReportToHtmlFile(jasperPrint.toString())
        /**
         * 3- export to Excel sheet
         */
        val exporter: JRXlsxExporter? = JRXlsxExporter()
        val reportConfigXLS = SimpleXlsxReportConfiguration()
        reportConfigXLS.sheetNames = arrayOf("Report")
        exporter!!.setConfiguration(reportConfigXLS)
        exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        exporter.exporterOutput = SimpleOutputStreamExporterOutput(response.outputStream)
        response.setHeader("Content-Disposition", "attachment;filename=ReportProduct.xlsx")
        response.contentType = "application/octet-stream"
        exporter.exportReport()
        return "Export Success"
    }

    override fun generateReportPurchase(startDate: Date, endDate: Date): MutableMap<String, Any> {
        val check = purchaseReceiveOrderRepository.findPurchaseOrderRecieve(startDate, endDate)
        if (check.isEmpty()) {
            return responseObjectMap.responseOBJ(501 , "Not have data !!")
        } else {
            val p: List<PRecieveDTO> = purchaseReceiveOrderRepository.reportPurchase(startDate, endDate)
            val file: File = ResourceUtils.getFile("classpath:ReportPurchase.jrxml")
            val jasperReport = JasperCompileManager.compileReport(file.absolutePath)
            val dataSource = JRBeanCollectionDataSource(p)
            val parameters: MutableMap<String, Any> = HashMap()
            parameters["start_date"] = startDate
            parameters["end_date"] = endDate
            val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource)
            /**
             * 3- export to Excel sheet
             */
            val exporter: JRXlsxExporter = JRXlsxExporter()
            val reportConfigXLS = SimpleXlsxReportConfiguration()
            reportConfigXLS.sheetNames = arrayOf("ReportPurchase")
            exporter.setConfiguration(reportConfigXLS)
            exporter.setExporterInput(SimpleExporterInput(jasperPrint))
            exporter.exporterOutput = SimpleOutputStreamExporterOutput(response.outputStream)
            response.setHeader("Content-Disposition", "attachment;filename=Purchase.xlsx")
            response.contentType = "application/octet-stream"
            exporter.exportReport()
            return responseObjectMap.responseObj("Success")
        }
    }

    override fun generateReportAdjustment(adjustment: String, startDate: Date, endDate: Date): MutableMap<String, Any> {
        val check = adjustmentRepository.findAdjustment(adjustment, startDate, endDate)
        if (check.isEmpty()) {
            return responseObjectMap.responseOBJ(501, "Data not have !!")
        } else {
            val p: List<ReportAdjDto> = adjustmentRepository.adjustmentReport(adjustment, startDate, endDate)
            val file: File = ResourceUtils.getFile("classpath:ReportAdj.jrxml")
            val jasperReport = JasperCompileManager.compileReport(file.absolutePath)
            val dataSource = JRBeanCollectionDataSource(p)
            val parameters: MutableMap<String, Any> = HashMap()
            parameters["startDate"] = startDate
            parameters["endDate"] = endDate
            parameters["adjustment"] = adjustment
            val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource)

            /**
             * 3- export to Excel sheet
             */
            val exporter: JRXlsxExporter = JRXlsxExporter()
            val reportConfigXLS = SimpleXlsxReportConfiguration()
            reportConfigXLS.sheetNames = arrayOf("ReportAddjustment")
            exporter.setConfiguration(reportConfigXLS)
            exporter.setExporterInput(SimpleExporterInput(jasperPrint))
            exporter.exporterOutput = SimpleOutputStreamExporterOutput(response.outputStream)
            response.setHeader("Content-Disposition", "attachment;filename=Adjustment.xlsx")
            response.contentType = "application/octet-stream"
            exporter.exportReport()
            return responseObjectMap.responseObj("Success !!")
        }
    }
}
