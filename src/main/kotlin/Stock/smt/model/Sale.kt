package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*
import java.util.*
@Entity
@Table(name="Sale")
class Sale (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var code: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var customer: Customer? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var employee: Employee? = null,
    var saleDate: Date,
    var description: String,
    var create_by: String,
    var create_date: LocalDateTime,
    var update_by: String,
    var update_date: LocalDateTime,
    var status: Boolean = true,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    var saleDetail:MutableList<SaleDetail>? = null
)