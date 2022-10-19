package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.*
import javax.validation.constraints.NotNull
import  java.util.*

@Entity
@Table(name="employees")
class Employee  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    @NotNull
    var name: String,
    var gender: String,
    @NotNull
    var contact: String,
    @Email
    var email: String,
    @JsonFormat(pattern="yyyy-MM-dd")
    var dob: Date,
    var marital_status: String,
    var address: String,
    @NotNull
    var position: String,
    var photo: String? = null,
    var status: Boolean = true,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employee")
    var purchaseOrder:MutableList< PurchaseOrder>? =null,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    var purchaseReceive: MutableList<PurchaseReceive>? = null,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    var adjustmentDetail: MutableList<AdjustmentDetail>? = null

)