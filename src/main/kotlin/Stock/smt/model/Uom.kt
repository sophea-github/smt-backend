package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "uom")
class Uom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "uom",fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    var uom: MutableList<ItemVariantUom>? = null,

    var status: Boolean = true
)