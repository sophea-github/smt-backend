package Stock.smt.service.serviceImpl

import Stock.smt.model.ChangeRate
import Stock.smt.repository.ChangeRateRepository
import Stock.smt.service.ChangeRateService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ChangeRateServiceImpl constructor(
    var changeRateRepository: ChangeRateRepository
) : ChangeRateService {
    override fun findAll(): List<ChangeRate>?= changeRateRepository.findAll()
    override fun saveAll(t: ChangeRate): ChangeRate? = changeRateRepository.save(t)
    override fun updateObj(id: Int, t: ChangeRate): ChangeRate? {
        val cr = changeRateRepository.findByIdAndStatusIsTrue(id)
        cr?.type = t.type
        cr?.rate = t.rate
        cr?.symbol = t.symbol
        cr?.description = t.description
        return changeRateRepository.save(cr!!)
    }
    override fun delete(id: Int) {
        try {
            changeRateRepository.deleteById(id)
        }catch(e: Exception){}
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<ChangeRate> {
        TODO("Not yet implemented")
    }
}