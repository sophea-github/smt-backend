package Stock.smt.service.serviceImpl

import Stock.smt.model.Customer
import Stock.smt.repository.CustomerRepository
import Stock.smt.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl: CustomerService {
    @Autowired
    lateinit var customerRepository: CustomerRepository
    override fun findAll(): List<Customer>? = customerRepository.findAll()
    override fun saveAll(t: Customer): Customer? = customerRepository.save(t)
    override fun updateObj(id: Int, t: Customer): Customer? {
        val cs = customerRepository.findCustomerById(id)
        cs?.company = t.company
        cs?.name = t.name
        cs?.contact = t.contact
        cs?.email = t.email
        cs?.address = t.address
        cs?.description = t.description
        return customerRepository.save(cs!!)
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<Customer> {
        TODO("Not yet implemented")
    }
    override fun delete(id: Int) = customerRepository.deleteById(id)
}