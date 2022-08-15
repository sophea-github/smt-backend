package Stock.smt.service.serviceImpl

import Stock.smt.model.Purchase_Order_Detail
import Stock.smt.repository.Purchase_Order_DetailRepository
import Stock.smt.service.Purchase_Order_DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class Purchase_Order_DetailServiceImpl: Purchase_Order_DetailService {
    @Autowired
    lateinit var podrepository: Purchase_Order_DetailRepository

    override fun findAll(): List<Purchase_Order_Detail>? = podrepository.findAll()

    override fun saveAll(t: Purchase_Order_Detail): Purchase_Order_Detail? {
        TODO("Not yet implemented")
    }

    override fun updateObj(id: Int, t: Purchase_Order_Detail): Purchase_Order_Detail? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<Purchase_Order_Detail> {
        TODO("Not yet implemented")
    }
}