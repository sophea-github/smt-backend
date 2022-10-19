package Stock.smt.repository

import Stock.smt.model.Custom.DTO.PorDTO
import Stock.smt.model.Custom.DTO.PurchaseOrderDTO
import Stock.smt.model.Custom.DTO.PurchaseReceiveDTO
import Stock.smt.model.PurchaseReceive
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PurchaseReceiveOrderRepository : JpaRepository<PurchaseReceive, Int> {

    fun findByIdAndStatusIsTrue(id: Int): PurchaseReceive?

    @Query("SELECT p.id AS id, p.code as code, p.receive_date as receive_date, p.supplier.id as supplier_id , p.supplier.company as supplier_company ,p.employee.id AS employee_id,p.employee.name as employeeName,p.employee.photo as photo,p.purchase_order.employee.name as order_by , p.purchase_order.employee.photo as order_empPhoto ,p.changeRate.id AS changeRate_Id,p.changeRate.symbol as changeRateSymbol,p.purchase_order.order_date AS order_date," +
            "SUM(pd.purchaseOrderDetail.price * (pd.qty / pd.purchaseOrderDetail.itemVariantUom.conversion_factor)) AS totalPrice , " +
            "COUNT(pd.product) AS totalItem , p.description AS description,p.create_by AS create_by,p.create_date AS create_date,p.status AS status " +
            "FROM PurchaseReceive p " +
            "JOIN PurchaseReceiveDetail pd ON (p.id = pd.purchaseReceive.id) " +
            "GROUP BY id, code , receive_date , supplier_id , supplier_company ,employee_id,employeeName, photo , order_by , order_empPhoto , changeRate_Id , changeRateSymbol , order_date, description,create_by,create_date,status ")
    @Transactional
    fun findCountPor(): List<PurchaseReceiveDTO>

    @Query("SELECT pr FROM PurchaseReceive pr JOIN PurchaseReceiveDetail pd ON pr.id = pd.purchaseReceive.id " +
            "WHERE pr.id= ?1")
    @Transactional
    fun findPoReceive(id: Int): PorDTO

}