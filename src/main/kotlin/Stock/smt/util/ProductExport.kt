package Stock.smt.util

import Stock.smt.model.Product
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.IOException
import javax.servlet.http.HttpServletResponse
class ProductExport  {

    private var workbook: XSSFWorkbook? = null
    private var sheet: XSSFSheet? = null
    private var product: List<Product>? = null
    constructor(product: List<Product>?) {
        this.product = product
        workbook = XSSFWorkbook()
    }


    fun writeHeader() {
        sheet = workbook!!.createSheet("Product")

//        Banner
        val rowM: Row = sheet!!.createRow(1)
        val cell = rowM.createCell(1)
        val styleM: CellStyle = workbook!!.createCellStyle()
        val fontM = workbook!!.createFont()
        fontM.bold = true
        fontM.setFontName("Times New Roman");
        fontM.setFontHeight(25.0)
        styleM.setFont(fontM)
        cell.setCellValue("List Product Of Stock Management")
        cell.setCellStyle(styleM);
        styleM.setAlignment(HorizontalAlignment.CENTER);
        styleM.setVerticalAlignment(VerticalAlignment.CENTER);
        val cellRangeAddress = CellRangeAddress(1, 4, 1, 9)
        sheet!!.addMergedRegion(cellRangeAddress)
//        RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);


//        header
        val row: Row = sheet!!.createRow(5)
        val style: CellStyle = workbook!!.createCellStyle()
        val font = workbook!!.createFont()
        font.bold = true
        font.setFontName("Times New Roman");
        font.setFontHeight(14.0)
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font)
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        createCell(row, 1, "ID", style)
        createCell(row, 2, "Code", style)
        createCell(row, 3, "Name", style)
        createCell(row, 4, "Category", style)
        createCell(row, 5, "UOM", style)
        createCell(row, 6, "Quantity", style)
        createCell(row, 7, "Amount", style)
        createCell(row, 8, "Price", style)
        createCell(row, 9, "Active", style)
    }

    private fun createCell(row: Row, columnCount: Int, value: Any, style: CellStyle) {
        sheet!!.autoSizeColumn(columnCount)
        val cell = row.createCell(columnCount)
        if (value is Double) {
            cell.setCellValue(value)
        } else if (value is Boolean) {
            cell.setCellValue(value)
        } else if (value is String) {
            cell.setCellValue(value)
        }
        cell.cellStyle = style
    }

    private fun writeDataLines() {
        var rowCount = 6
        val style: CellStyle = workbook!!.createCellStyle()
        val font = workbook!!.createFont()
        font.setFontHeight(13.0)
        font.setFontName("Times New Roman");
        style.setFont(font)
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        for (product in product!!) {
            val row: Row = sheet!!.createRow(rowCount++)
            var columnConut = 1
            createCell(row, columnConut++, product.id.toDouble(), style)
            createCell(row, columnConut++, product.code, style)
            createCell(row, columnConut++, product.name, style)
            createCell(row, columnConut++, product.category!!.name, style)
            createCell(row, columnConut++, product.itemVariantUom!!.item_variant_name, style)
            createCell(row, columnConut++, product.qty.toDouble(), style)
            createCell(row, columnConut++, product.amt.toDouble(), style)
            createCell(row, columnConut++, product.price.toDouble(), style)
            createCell(row, columnConut++, product.active!!, style)
        }
    }

    @Throws(IOException::class)
    fun export(response: HttpServletResponse) {
        writeHeader()
        writeDataLines()
        val outputStream = response.outputStream
        workbook!!.write(outputStream)
        workbook!!.close()
        outputStream.close()
    }



}


