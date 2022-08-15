package Stock.smt.service.serviceImpl

import Stock.smt.model.Custom.Exception.CustomNotFoundException
import Stock.smt.model.Custom.ResponseObjectMap
import Stock.smt.model.Employee
import Stock.smt.model.Product
import Stock.smt.repository.CategoryRepository
import Stock.smt.repository.ItemVeriantUomRepository
import Stock.smt.repository.ProductRepository
import Stock.smt.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl : ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    @Autowired
    lateinit var productService: ProductService
    @Autowired
    lateinit var itemRepository: ItemVeriantUomRepository
    @Autowired
    lateinit var responseObjectMap: ResponseObjectMap

    override fun findAll(): List<Product>? = productRepository.findAll()

    override fun saveAll(t: Product): Product? = productRepository.save(t)

    override fun updateObj(id: Int, t: Product): Product? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) = productRepository.deleteById(id)

    override fun pagination(q: String?, page: Int, size: Int): Page<Product> {
        TODO("Not yet implemented")
    }

    override fun createProduct(catId: Int,itmId: Int, t: Product): Product? {
        try {
            if(!productRepository.existsByCode(t.code)){
                val pro : Product? = productService.saveAll(t)
                val cat = categoryRepository.findByIdAndStatusTrue(catId)
                val itm = itemRepository.findByIdAndStatusIsTrue(itmId)
                pro?.code = t.code
                pro?.name = t.name
                pro?.category = cat
                pro?.itemVariantUom = itm
                pro?.qty = t.qty
                pro?.amt = t.qty * itm!!.conversion_factor
                if (pro?.amt != 0 ){
                    pro?.active = "Available"
                }else
                {
                    pro?.active = "UnAvailable"
                }

                pro?.price = 0.0F
                pro?.photo = t.photo
                pro?.stock_alert = "adjustment"
                pro?.create_by = t.create_by
                pro?.description = t.description
                return productRepository.save(pro!!)
            }
        }catch (e: Exception){
        }
        return null
    }
    override fun updateProduct(cat_id: Int,itmId: Int, id: Int, t: Product): Product? {
        var cat = categoryRepository.findByIdAndStatusTrue(cat_id)
        var itm = itemRepository.findByIdAndStatusIsTrue(itmId)
        var proId = productRepository.findByIdAndStatusIsTrue(id)
        try {
                proId?.name = t.name
                proId?.itemVariantUom = itm
                proId?.category = cat
                proId?.qty = t.qty
                proId?.amt = t.qty * itm!!.conversion_factor
                proId?.price = t.price
                proId?.active= t.active
                if (proId?.amt != 0 ){
                    proId?.active = "Available"
                }else
                {
                    proId?.active = "UnAvailable"
                }
                proId?.photo = t.photo
                proId?.stock_alert = t.stock_alert
                proId?.create_by = t.create_by
                proId?.description = t.description
                return productRepository.save(proId!!)
        }catch (e: Exception){}
        return null
    }


    override fun uploadImg(id: Int, photo: String): Product {
        var ptId= productRepository.findByIdAndStatusIsTrue(id)
        ptId?.photo =photo
        return productRepository.save(ptId!!)
    }

}