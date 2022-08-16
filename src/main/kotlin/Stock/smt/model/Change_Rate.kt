package Stock.smt.model

import jdk.incubator.foreign.NativeSymbol
import org.springframework.web.bind.annotation.GetMapping
import javax.persistence.*

@Entity
@Table(name = "change_rate")
class Change_Rate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var type: String,
    var rate: Float,
    var symbol: String,
    var description: String,
    var status: Boolean = true
)
