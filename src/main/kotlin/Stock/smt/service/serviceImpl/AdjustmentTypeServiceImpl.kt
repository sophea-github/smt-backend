package Stock.smt.service.serviceImpl

import Stock.smt.model.AdjustmentType
import Stock.smt.repository.AdjustmentTypeRepository
import Stock.smt.service.AdjustmentTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AdjustmentTypeServiceImpl: AdjustmentTypeService {
    @Autowired lateinit var adjustmentTypeRepository: AdjustmentTypeRepository

    override fun findAll(): List<AdjustmentType>? {
        return adjustmentTypeRepository.findAll()
    }

    override fun delete(id: Int) {
        return adjustmentTypeRepository.deleteById(id)
    }

    override fun saveAll(t: AdjustmentType): AdjustmentType? {
        t.create_date = LocalDateTime.now()
        return adjustmentTypeRepository.save(t)
    }

    override fun updateObj(id: Int, t: AdjustmentType): AdjustmentType? {
        var adjustment = adjustmentTypeRepository.findByIdAndStatusIsTrue(id);
//        adjustment!!.id = t.id
        adjustment!!.type = t.type
        adjustment!!.create_date = LocalDateTime.now()
        adjustment!!.create_by = t.create_by
        return adjustmentTypeRepository.save(adjustment!!)
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<AdjustmentType> {
        TODO("Not yet implemented")
    }

}