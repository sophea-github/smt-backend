package Stock.smt.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*

@Entity
@Table(name = "documentery_setting")
class DocumentarySetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var prefix: String,
    var lastcode: Int,
    var length: Int,
    var description: String,
    var status: Boolean= true
)