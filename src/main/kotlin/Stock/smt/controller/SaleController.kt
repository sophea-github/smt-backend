package Stock.smt.controller

import Stock.smt.model.custom.dto.SaleRequest
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.service.SaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SaleController {
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap
    @Autowired
    lateinit var saleService: SaleService

    @GetMapping("/sale")
    fun getSale(): MutableMap<String,Any>{
        return responseObjectMap.responseObj(saleService.getSale())
    }

    @PostMapping("/sale/{empId}/{cusId}")
    fun newSale(@PathVariable empId: Int, @PathVariable cusId: Int,@RequestBody sale: SaleRequest): MutableMap<String,Any>{
        return responseObjectMap.responseObj(saleService.newSale(empId,cusId,sale))
    }
    @PutMapping("/sale/{empId}/{cusId}/{id}")
    fun saleUpdate(@PathVariable empId: Int, @PathVariable cusId: Int, @PathVariable id: Int, @RequestBody sale: SaleRequest): MutableMap<String, Any>{
        return responseObjectMap.responseObj(saleService.updateSale(empId,cusId,id,sale))
    }
    @DeleteMapping("/sale/{id}")
    fun saleDelete(@PathVariable id: Int): MutableMap<String,Any>{
        return responseObjectMap.responseObj(saleService.deleteSale(id))
    }

}