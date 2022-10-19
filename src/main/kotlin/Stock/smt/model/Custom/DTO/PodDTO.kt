package Stock.smt.model.Custom.DTO

import Stock.smt.model.ItemVariantUom
import Stock.smt.model.Product
import Stock.smt.model.PurchaseOrder
import Stock.smt.model.PurchaseReceiveDetail
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

interface PodDTO {

    var id: Int?
    var product: Product?
//    var itemVariantUom: ItemVariantUom?
    var purchaseOrder: PurchaseOrder?
    var qty: Int?
    var price: Double?
    var create_by: String?
    var create_date: LocalDateTime?
    var purchaseReceiveDetail:MutableList<PurchaseReceiveDetail>?



}