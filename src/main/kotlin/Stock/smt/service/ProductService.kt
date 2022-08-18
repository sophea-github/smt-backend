package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Employee
import Stock.smt.model.ItemVariantUom
import Stock.smt.model.Product

interface ProductService: BaseFun<Product,Int> {

    fun createProduct(catId: Int,itmId: Int, product: Product) : Product?
    fun updateProduct(cat_id: Int,itmId: Int,id: Int , product: Product): Product?
    fun uploadImg(id: Int,photo: String): Product?

    fun updateStockProduct(id: Int, qty: Int): Product
}