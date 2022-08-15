package Stock.smt.service.serviceImpl

import Stock.smt.model.Supplier
import Stock.smt.repository.SupplierRepository
import Stock.smt.service.SupplierService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SupplierServiceImpl : SupplierService {
    @Autowired
    lateinit var supplierRepository: SupplierRepository

    override fun pagination(q: String?, page: Int, size: Int): Page<Supplier> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Supplier>? = supplierRepository.findAll()

    override fun saveAll(t: Supplier): Supplier? = supplierRepository.save(t)

    override fun updateObj(id: Int, t: Supplier): Supplier? {
        var sup = supplierRepository.findByIdAndStatusTrue(id)
        sup?.company = t.company
        sup?.contact = t.contact
        sup?.email = t.email
        sup?.address = t.address
        sup?.description = t.description
        return supplierRepository.save(sup!!)
    }

    override fun delete(id: Int) = supplierRepository.deleteById(id)
}