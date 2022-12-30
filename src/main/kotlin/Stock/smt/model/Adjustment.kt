package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
@Entity
@Table(name = "adjustment")
class Adjustment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var code: String? = null,
    var adjustmentDate: Date,
    @ManyToOne(fetch = FetchType.LAZY)
    var adjustmentType: AdjustmentType? = null,
    var create_by: String? = null,
    var create_date: LocalDateTime? = null,
    var update_by: String? = null,
    var update_date: LocalDateTime? = null,
    var description: String? = null,
    var status: Boolean = true,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "adjustment")
    var adjustmentDetail: MutableList<AdjustmentDetail>? = null

)