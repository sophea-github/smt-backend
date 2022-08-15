package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.ItemVariantUom

interface ItemVeriantUomService: BaseFun<ItemVariantUom,Int> {

    fun createUom_Detail(id: Int, uomDetail: ItemVariantUom) : ItemVariantUom?
    fun updateUom_Detail(uom_id: Int,id: Int , uomDetail: ItemVariantUom): ItemVariantUom?
}