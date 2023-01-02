package Stock.smt.service.serviceImpl

import Stock.smt.model.custom.ResponseObjectMap
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
    override fun delete(id: Int) {

        try {
            productRepository.deleteById(id)
        }catch (e: Exception){}
    }
    override fun pagination(q: String?, page: Int, size: Int): Page<Product> {
        TODO("Not yet implemented")
    }
    override fun createProduct(catId: Int,itmId: Int, product: Product): Product? {
        try {
            if(!productRepository.existsByCode(product.code)){
                val pro : Product? = productService.saveAll(product)
                val cat = categoryRepository.findByIdAndStatusTrue(catId)
                val itm = itemRepository.findByIdAndStatusIsTrue(itmId)
                pro?.code = product.code
                pro?.name = product.name
                pro?.category = cat
                pro?.itemVariantUom = itm
                pro?.qty = product.qty
                pro?.amt = product.qty * itm!!.conversion_factor
                if (pro?.amt != 0 ){
                    pro?.active = "Available"
                }else
                {
                    pro.active = "UnAvailable"
                }

                pro?.price = 0.0F
                pro?.photo = product.photo
                pro?.stock_alert = "adjustment"
                pro?.create_by = product.create_by
                pro?.description = product.description
                return productRepository.save(pro!!)
            }
        }catch (e: Exception){
        }
        return null
    }
    override fun updateProduct(cat_id: Int,itmId: Int, id: Int, product: Product): Product? {
        val cat = categoryRepository.findByIdAndStatusTrue(cat_id)
        val itm = itemRepository.findByIdAndStatusIsTrue(itmId)
        val proId = productRepository.findByIdAndStatusIsTrue(id)
        try {
                proId?.code = product.code
                proId?.name = product.name
                proId?.itemVariantUom = itm
                proId?.category = cat
                proId?.qty = product.qty
                proId?.amt = product.qty * itm!!.conversion_factor
                proId?.price = product.price
                proId?.active= product.active
                if (proId?.amt != 0 ){
                    proId?.active = "Available"
                }else {
                    proId.active = "UnAvailable"
                }
                proId?.photo = product.photo
                proId?.stock_alert = product.stock_alert
                proId?.create_by = product.create_by
                proId?.description = product.description
                return productRepository.save(proId!!)
        }catch (e: Exception){}
        return null
    }
    override fun updateStockProduct(id: Int, qty: Int): Product {
        val pid = productRepository.findByIdAndStatusIsTrue(id)
        pid?.qty = pid!!.qty.plus(qty)
        pid.amt = pid.itemVariantUom!!.conversion_factor * qty
        if(pid.qty > 0)
        {
            pid.active = "Available"
        }else{
            pid.active = "UnAvailable"
        }
        return  productRepository.save(pid)
    }
    override fun uploadImg(id: Int, photo: String): Product {
        val ptId= productRepository.findByIdAndStatusIsTrue(id)
        ptId?.photo =photo
        return productRepository.save(ptId!!)
    }
    override fun findProductById(id: Int):Product? = productRepository.findByIdAndStatusIsTrue(id)

}