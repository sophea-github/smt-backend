package Stock.smt.repository

import Stock.smt.model.custom.dto.PurchaseOrderDTO
import Stock.smt.model.custom.dto.PoDTO
import Stock.smt.model.PurchaseOrder
import Stock.smt.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PurchaseOrderRepository: JpaRepository<PurchaseOrder, Int> {
    fun findByIdAndStatusIsTrue(id: Int): PurchaseOrder?
    fun existsByCodeAndSupplier(code: String, supplier:Supplier): Boolean
    @Query("SELECT p.id AS id, p.code as code, p.supplier.id as supplier_id , p.supplier.company as supplier_company , p.supplier.email as supplier_email ," +
            " p.supplier.contact as supplier_contact , p.supplier.address as supplier_address ,p.employee.id AS employee_id,p.employee.name as employeeName,p.employee.photo as photo,p.changeRate.id AS changeRate_Id,p.changeRate.symbol as changeRateSymbol,p.order_date AS order_date," +
            "SUM(pd.price * (pd.qty / pd.itemVariantUom.conversion_factor)) AS totalPrice , " +
            "COUNT(pd.product) AS totalItem , p.description AS description,p.create_by AS create_by,p.create_date AS create_date,p.status AS status " +
            "FROM PurchaseOrder p " +
            "JOIN PurchaseOrderDetail pd ON (p.id = pd.purchaseOrder.id) " +
            "GROUP BY id, code , supplier_id , supplier_company , supplier_email , supplier_contact , supplier_address ,employee_id,employeeName, photo , changeRate_Id , changeRateSymbol , order_date, description,create_by,create_date,status ")
    @Transactional
    fun findCountPo(): List<PurchaseOrderDTO>
    @Query("SELECT p FROM PurchaseOrder p JOIN PurchaseOrderDetail pd ON p.id = pd.purchaseOrder.id " +
            "WHERE p.id= ?1")
    @Transactional
    fun findPurchase(id: Int): PoDTO
}