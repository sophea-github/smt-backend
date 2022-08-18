package Stock.smt.service.serviceImpl

import Stock.smt.model.Uom
import Stock.smt.repository.UomRepository
import Stock.smt.service.UomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class UomServiceImpl: UomService  {
    @Autowired
    lateinit var uomRepository: UomRepository
    @Autowired

    override fun findAll(): List<Uom>? = uomRepository.findAll()
    override fun saveAll(t: Uom): Uom? = uomRepository.save(t)
    override fun updateObj(id: Int, t: Uom): Uom? {
        var um = uomRepository.findByIdAndStatusIsTrue(id)
        um?.name = t.name
        return uomRepository.save(um!!)
    }

    override fun delete(id: Int) {
        try {
            uomRepository.deleteById(id)
        }catch (e: Exception){}
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<Uom> {
        TODO("Not yet implemented")
    }
}