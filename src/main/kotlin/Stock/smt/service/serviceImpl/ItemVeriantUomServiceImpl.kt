package Stock.smt.service.serviceImpl

import Stock.smt.model.ItemVariantUom
import Stock.smt.repository.ItemVeriantUomRepository
import Stock.smt.repository.UomRepository
import Stock.smt.service.ItemVeriantUomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ItemVeriantUomServiceImpl: ItemVeriantUomService {
    @Autowired
    lateinit var itemVeriantUomRepository: ItemVeriantUomRepository
    @Autowired
    lateinit var uomRepository: UomRepository
    @Autowired
    lateinit var uomDetailService: ItemVeriantUomService

    override fun findAll(): List<ItemVariantUom>? = itemVeriantUomRepository.findAll()

    override fun saveAll(t: ItemVariantUom): ItemVariantUom? =  itemVeriantUomRepository.save(t)
    override fun updateObj(id: Int, t: ItemVariantUom): ItemVariantUom? {
        TODO()
    }

    override fun delete(id: Int) {
        try {
            itemVeriantUomRepository.deleteById(id)
        }catch (e: Exception){}
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<ItemVariantUom> {
        TODO("Not yet implemented")
    }

    override fun createUom_Detail(id: Int, uomDetail: ItemVariantUom): ItemVariantUom? {
        var udetail: ItemVariantUom? = uomDetailService.saveAll(uomDetail)
        var uomById = uomRepository.findByIdAndStatusIsTrue(id)
        udetail?.item_variant_name = uomDetail.item_variant_name
        udetail?.unit_value = uomDetail.unit_value
        udetail?.conversion_factor = uomDetail.conversion_factor
        udetail?.uom = uomById
        return itemVeriantUomRepository.save(uomDetail)
    }

    override fun updateUom_Detail(uom_id: Int, id: Int, t: ItemVariantUom): ItemVariantUom? {
        var um_id = uomRepository.findByIdAndStatusIsTrue(uom_id)
        var um_detail = itemVeriantUomRepository.findByIdAndStatusIsTrue(id)
        um_detail?.item_variant_name = t.item_variant_name
        um_detail?.unit_value = t.unit_value
        um_detail?.conversion_factor = t.conversion_factor
        um_detail?.description = t.description
        um_detail?.uom = um_id
        return itemVeriantUomRepository.save(um_detail!!)
    }

}