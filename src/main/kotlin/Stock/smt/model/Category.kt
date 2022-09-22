package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "category")
class Category (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var name: String,
    var description: String? = null,
    var status: Boolean = true,

//    @JsonIgnore
//    @OneToMany(mappedBy = "category",cascade = [CascadeType.ALL],fetch = FetchType.LAZY)
//    var subCategory: MutableList<SubCategory>? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    var product: MutableList<Product>? = null

)