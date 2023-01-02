package Stock.smt.repository

import Stock.smt.model.custom.dto.PorDTO
import Stock.smt.model.custom.dto.PurchaseReceiveDTO
import Stock.smt.model.PurchaseReceive
import Stock.smt.model.custom.dto.PRecieveDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

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

    @Query("select pd.code as code, emp.name as name, pd.name as product,itm.item_variant_name as item_variant_name, prd.qty as qty , pod.price as price ,cr.symbol as symbol\n" +
            ", sum((prd.qty /itm.conversion_factor) * pod.price) as subtotal from PurchaseReceive pr \n" +
            "join PurchaseReceiveDetail prd on pr.id =  prd.purchaseReceive.id \n" +
            "join Employee emp on pr.employee.id = emp.id \n" +
            "join Product pd on prd.product.id = pd.id \n" +
            "join PurchaseOrderDetail pod on pod.id =  prd.purchaseOrderDetail.id \n" +
            "join ItemVariantUom itm on pd.itemVariantUom.id = itm.id\n" +
            "join ChangeRate cr on pr.changeRate.id = cr.id\n" +
            "where pr.receive_date >= ?1 \n" +
            "and pr.receive_date <= ?2 \n" +
            "GROUP BY pd.code,emp.name, pd.name , itm.item_variant_name, prd.qty , pod.price, cr.symbol")
    @Transactional
    fun reportPurchase(startDate: Date, endDate: Date): List<PRecieveDTO>

    @Query("""
        SELECT pr FROM PurchaseReceive pr 
        WHERE 
        pr.receive_date >= :startDate 
        AND 
        pr.receive_date <= :endDate
    """)
    fun findPurchaseOrderRecieve(startDate: Date, endDate: Date): List<PurchaseReceive>
}