package Stock.smt.service.serviceImpl

import Stock.smt.model.Change_Rate
import Stock.smt.repository.Change_RateRepository
import Stock.smt.service.Change_RateService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class Change_RateServiceImpl constructor(
    var changeRaterepository: Change_RateRepository
) : Change_RateService {
    override fun findAll(): List<Change_Rate>?= changeRaterepository.findAll()
    override fun saveAll(t: Change_Rate): Change_Rate? = changeRaterepository.save(t)
    override fun updateObj(id: Int, t: Change_Rate): Change_Rate? {
        var cr = changeRaterepository.findByIdAndStatusIsTrue(id)
        cr?.type = t.type
        cr?.rate = t.rate
        cr?.symbol = t.symbol
        cr?.description = t.description
        return changeRaterepository.save(cr!!)
    }
    override fun delete(id: Int) = changeRaterepository.deleteById(id)

    override fun pagination(q: String?, page: Int, size: Int): Page<Change_Rate> {
        TODO("Not yet implemented")
    }
}