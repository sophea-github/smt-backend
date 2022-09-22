package Stock.smt.repository

import Stock.smt.model.Custom.DTO.PurchaseOrderDetailDTO
import Stock.smt.model.PurchaseOrder
import Stock.smt.model.PurchaseOrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PurchaseOrderDetailRepository: JpaRepository<PurchaseOrderDetail, Int> {

    fun findByIdAndStatusIsTrue(id: Int): PurchaseOrderDetail?
    fun findByPurchaseOrderId(id: Int) : PurchaseOrderDetail?

    @Query("SELECT pd.id as id,p.code as code,p.employee as employee,pd.product as product,pd.qty as qty,pd.price as price,pd.create_by as create_by,pd.create_date as create_date,pd.itemVariantUom as itemVariantUom ,pd.status as status,pd.purchaseOrder FROM " +
            "PurchaseOrderDetail pd JOIN PurchaseOrder p ON p.id = pd.purchaseOrder.id " +
            "WHERE pd.purchaseOrder.id= ?1")
    @Transactional
    fun findPodByPoId(poId: Int): List<PurchaseOrderDetailDTO>?

    @Query("SELECT pd FROM PurchaseOrderDetail pd WHERE pd.product.id = ?1 AND pd.purchaseOrder.id = ?2")
    @Transactional
    fun findPod(proId: Int, podId: Int): PurchaseOrderDetail?

    @Modifying
    @Query("DELETE FROM PurchaseOrderDetail pd WHERE pd.purchaseOrder.id = ?1")
    @Transactional
    fun deletePoDByPoId(poId: Int)



}