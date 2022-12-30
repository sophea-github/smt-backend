package Stock.smt.service.serviceImpl

import Stock.smt.model.SaleDetail
import Stock.smt.service.SaleDetailService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SaleDetailServiceImpl: SaleDetailService {

    override fun findAll(): List<SaleDetail>? {
        TODO("Not yet implemented")
    }
    override fun saveAll(t: SaleDetail): SaleDetail? {
        TODO("Not yet implemented")
    }
    override fun updateObj(id: Int, t: SaleDetail): SaleDetail? {
        TODO("Not yet implemented")
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<SaleDetail> {
        TODO("Not yet implemented")
    }
    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}