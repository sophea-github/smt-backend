package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.exception.CustomNotFoundException
import Stock.smt.model.Employee
import Stock.smt.model.custom.ResponseObjectMap
import Stock.smt.repository.EmployeeRepository
import Stock.smt.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl: EmployeeService {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    override fun pagination(q: String?, page: Int, size: Int): Page<Employee> {
        TODO("Not yet implemented")
    }
    override fun findAll(): List<Employee>? = employeeRepository.findAll()
    override fun findById(id: Int): Employee? = employeeRepository.findByIdAndStatusTrue(id)
    override fun saveAll(t: Employee): Employee? = employeeRepository.save(t)
    override fun updateObj(id: Int, t: Employee): Employee? {
        val emp = employeeRepository.findByIdAndStatusTrue(id)
        emp?.name = t.name
        emp?.gender = t.gender
        emp?.contact = t.contact
        emp?.email = t.email
        emp?.dob = t.dob
        emp?.address = t.address
        emp?.photo = t.photo
        emp?.position = t.position
        emp?.marital_status = t.marital_status
        return employeeRepository.save(emp!!)
    }
    override fun delete(id: Int) {
        try {
            employeeRepository.deleteById(id)
        }catch (e: Exception){}

    }

    override fun addEmployee(t: Employee): MutableMap<String, Any>? {
        val check = employeeRepository.findByContactOrEmail(t.contact,t.email)
        return responseObjectMap.responseObject(
            if (check == null) {
                saveAll(t)
            } else {
                return responseObjectMap.responseOBJ(403, "Data not found !!")
            }
        )
    }

    override fun updateEmployee(id: Int, t: Employee): MutableMap<String, Any>? {
        val c = employeeRepository.findByContact(t.contact)
        val e = employeeRepository.findByEmail(t.email)
        val emp = employeeRepository.findByIdAndStatusTrue(id)
        return responseObjectMap.responseObject(
            if (
                (e?.id == emp?.id && c?.id == emp?.id) ||
                (c?.id == null && e?.id == null) ||
                (e?.id == emp?.id && c?.id == null) ||
                (c?.id == emp?.id && e?.id == null)
            ) {
                emp?.name = t.name
                emp?.gender = t.gender
                emp?.contact = t.contact
                emp?.email = t.email
                emp?.dob = t.dob
                emp?.address = t.address
                emp?.photo = t.photo
                emp?.position = t.position
                emp?.marital_status = t.marital_status
                employeeRepository.save(emp!!)
            } else {
                return responseObjectMap.responseOBJ(403, "Data can't updated !!")
            }
        )
    }
    override fun uploadImg(id: Int, photo: String): Employee? {
        val emp = employeeRepository.findByIdAndStatusTrue(id) ?: throw CustomNotFoundException("Id Not Found: $id")
        emp.photo = photo
        return employeeRepository.save(emp)
    }
}