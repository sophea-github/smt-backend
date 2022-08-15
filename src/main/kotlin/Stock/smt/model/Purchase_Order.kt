package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name="purchase_order")
class Purchase_Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    var employee: Employee? = null,

    @DateTimeFormat(style = "dd/MM/yyyy")
    var order_date: Date,
    var active: String? = null,
    var create_by: String? = null,
    var description: String,
    var status:Boolean = true,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "purchaseOrder")
    var purchaseOrderDetail: MutableList<Purchase_Order_Detail>? = null

)