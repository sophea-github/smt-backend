package Stock.smt.repository

import Stock.smt.model.PurchaseOrderDetail
import Stock.smt.model.PurchaseReceiveDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PurchaseReceiveOrderDetailRepository: JpaRepository<PurchaseReceiveDetail, Int> {

    fun findByIdAndStatusIsTrue(id: Int): PurchaseReceiveDetail?

    @Query("SELECT pd FROM PurchaseOrderDetail pd WHERE pd.product.id = ?1")
    @Transactional
    fun findProductOrder(proId: Int): PurchaseOrderDetail?

    @Query("SELECT prd FROM PurchaseReceiveDetail prd WHERE prd.product.id = ?1 AND prd.purchaseReceive.id = ?2 ")
    @Transactional
    fun findPord(proId: Int, pordId: Int): PurchaseReceiveDetail?

}