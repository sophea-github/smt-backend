package Stock.smt.service

import Stock.smt.base.BaseFun
import Stock.smt.model.ItemVariantUom

interface ItemVeriantUomService: BaseFun<ItemVariantUom,Int> {

    fun createUomDetail(id: Int, uomDetail: ItemVariantUom) : ItemVariantUom?
    fun updateUomDetail(uom_id: Int,id: Int , uomDetail: ItemVariantUom): ItemVariantUom?
}