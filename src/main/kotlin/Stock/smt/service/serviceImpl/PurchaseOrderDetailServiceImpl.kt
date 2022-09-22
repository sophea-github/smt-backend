package Stock.smt.service.serviceImpl

import Stock.smt.model.Custom.DTO.PurchaseOrderDetailDTO
import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.repository.PurchaseOrderDetailRepository
import Stock.smt.service.PurchaseOrderDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PurchaseOrderDetailServiceImpl: PurchaseOrderDetailService {
    @Autowired
    lateinit var podRepository: PurchaseOrderDetailRepository

    override fun findAll(): List<PurchaseOrderDetail>? = podRepository.findAll()

    override fun saveAll(t: PurchaseOrderDetail): PurchaseOrderDetail? {
        TODO("Not yet implemented")
    }

    override fun updateObj(id: Int, t: PurchaseOrderDetail): PurchaseOrderDetail? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<PurchaseOrderDetail> {
        TODO("Not yet implemented")
    }

    override fun findByIDPOD(poId: Int):List<PurchaseOrderDetailDTO>? {
        return podRepository.findPodByPoId(poId)
    }
}