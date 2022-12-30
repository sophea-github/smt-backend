package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "item_variant_uom")
class ItemVariantUom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_id")
    var uom: Uom? = null,
    @NotNull
    var item_variant_name: String,
    @NotNull
    var unit_value: Int,

    @NotNull
    var conversion_factor: Int,
    @NotNull
    var description: String,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(mappedBy = "itemVariantUom" ,fetch = FetchType.LAZY)
    var product: MutableList<Product>? =null,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "itemVariantUom")
    var purchaseOrderDetail: MutableList<PurchaseOrderDetail>? = null,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "itemVariantUom")
    var adjustmentDetail: MutableList<AdjustmentDetail>? = null,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "itemVariantUom")
    var saleDetail: MutableList<SaleDetail>? = null


)