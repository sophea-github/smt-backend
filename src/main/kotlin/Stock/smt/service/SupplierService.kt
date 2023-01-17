package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.Supplier

interface SupplierService : BaseFun<Supplier, Int> {

    fun addSupplier(t: Supplier): MutableMap<String,Any>?
    fun updateSupplier(id: Int,t: Supplier): MutableMap<String,Any>?


}