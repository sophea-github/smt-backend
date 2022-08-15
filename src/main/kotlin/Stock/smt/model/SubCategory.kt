package Stock.smt.model

import javax.persistence.*

@Entity
@Table(name = "subcategories")
class SubCategory (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var sub_name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    var description: String? = null,
    var status: Boolean = true

)