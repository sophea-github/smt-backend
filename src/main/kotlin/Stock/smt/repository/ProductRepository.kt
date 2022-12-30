package Stock.smt.repository

import Stock.smt.model.Product
import Stock.smt.model.custom.dto.ProductDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductRepository : JpaRepository<Product, Int> {
    fun findByIdAndStatusIsTrue(id: Int): Product?
    fun existsByCode(code: String): Boolean
    @Query("SELECT pd.code as code,ct.name as category, pd.name as product, itm.item_variant_name as uom , pd.qty as quantity , pd.price as price , pd.active  as status" +
            " from Product pd \n" +
            "join Category ct on pd.category.id = ct.id \n" +
            "join ItemVariantUom itm on pd.itemVariantUom.id= itm.id")
    @Transactional
    fun reportProduct(): List<ProductDTO>
}