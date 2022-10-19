package Stock.smt.model

import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "adjustmentType")
class AdjustmentType (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var type: String,
    var description: String? = null,
//    var active: Boolean?,
    var create_by: String? = null,
    var create_date: LocalDateTime? = null,
    var status: Boolean = true
)