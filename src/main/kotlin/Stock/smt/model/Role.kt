package Stock.smt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int,
    var name: String,
    var description: String,
    var status: Boolean = true,
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    var userRole: MutableList<User_Role>? = null

)