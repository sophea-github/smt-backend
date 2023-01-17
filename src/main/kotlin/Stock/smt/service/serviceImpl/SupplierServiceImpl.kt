package Stock.smt.service.serviceImpl

import Stock.smt.model.Supplier
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.model.custom.exception.CustomException
import Stock.smt.repository.SupplierRepository
import Stock.smt.service.SupplierService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SupplierServiceImpl : SupplierService {

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    override fun pagination(q: String?, page: Int, size: Int): Page<Supplier> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Supplier>? = supplierRepository.findAll()
    override fun saveAll(t: Supplier): Supplier? {
        val check = supplierRepository.findByContactOrEmail(t.contact, t.email)
        return if (check == null) {
            supplierRepository.save(t)
        } else {
            throw CustomException(403, "Data Not found !!")
        }
    }

    override fun addSupplier(t: Supplier): MutableMap<String, Any>? {
        val check = supplierRepository.findByContactOrEmail(t.contact, t.email)
        return responseObjectMap.responseObject(
            if (check == null) {
                supplierRepository.save(t)
            } else {
                return responseObjectMap.responseOBJ(403, "Data not found !!")
            }
        )
    }

    override fun updateSupplier(id: Int, t: Supplier): MutableMap<String, Any>? {
//        val check = supplierRepository.findByContactOrEmail(t.contact, t.email)
        val c = supplierRepository.findByContact(t.contact)
        val e = supplierRepository.findByEmail(t.email)
        val sup = supplierRepository.findByIdAndStatusTrue(id)
        return responseObjectMap.responseObject(
            if (
                (e?.id == sup?.id && c?.id == sup?.id) ||
                (c?.id == null && e?.id == null) ||
                (e?.id == sup?.id && c?.id == null) ||
                (c?.id == sup?.id && e?.id == null)
            ) {
                sup?.company = t.company
                sup?.contact = t.contact
                sup?.email = t.email
                sup?.address = t.address
                sup?.description = t.description
                supplierRepository.save(sup!!)
            } else {
                return responseObjectMap.responseOBJ(403, "Data can't updated !!")
            }
        )
    }

    override fun updateObj(id: Int, t: Supplier): Supplier? {
        val sup = supplierRepository.findByIdAndStatusTrue(id)
        sup?.company = t.company
        sup?.contact = t.contact
        sup?.email = t.email
        sup?.address = t.address
        sup?.description = t.description
        return supplierRepository.save(sup!!)

    }

    override fun delete(id: Int) {
        try {
            supplierRepository.deleteById(id)
        } catch (e: Exception) {
        }
    }
}