package Stock.smt.service.serviceImpl

import Stock.smt.model.ItemVariantUom
import Stock.smt.repository.ItemVeriantUomRepository
import Stock.smt.repository.UomRepository
import Stock.smt.service.ItemVeriantUomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ItemVariantUomServiceImpl: ItemVeriantUomService {
    @Autowired
    lateinit var itemVariantUomRepository: ItemVeriantUomRepository
    @Autowired
    lateinit var uomRepository: UomRepository
    @Autowired
    lateinit var uomDetailService: ItemVeriantUomService

    override fun findAll(): List<ItemVariantUom>? = itemVariantUomRepository.findAll()

    override fun saveAll(t: ItemVariantUom): ItemVariantUom? =  itemVariantUomRepository.save(t)
    override fun updateObj(id: Int, t: ItemVariantUom): ItemVariantUom? {
        TODO()
    }

    override fun delete(id: Int) {
        try {
            itemVariantUomRepository.deleteById(id)
        }catch (e: Exception){}
    }

    override fun pagination(q: String?, page: Int, size: Int): Page<ItemVariantUom> {
        TODO("Not yet implemented")
    }

    override fun createUomDetail(id: Int, uomDetail: ItemVariantUom): ItemVariantUom? {
        val uDetail: ItemVariantUom? = uomDetailService.saveAll(uomDetail)
        val uomById = uomRepository.findByIdAndStatusIsTrue(id)
        uDetail?.item_variant_name = uomDetail.item_variant_name
        uDetail?.unit_value = uomDetail.unit_value
        uDetail?.conversion_factor = uomDetail.conversion_factor
        uDetail?.uom = uomById
        return itemVariantUomRepository.save(uomDetail)
    }

    override fun updateUomDetail(uom_id: Int, id: Int, itemVariantUom: ItemVariantUom): ItemVariantUom? {
        val umId = uomRepository.findByIdAndStatusIsTrue(uom_id)
        val umDetail = itemVariantUomRepository.findByIdAndStatusIsTrue(id)
        umDetail?.item_variant_name = itemVariantUom.item_variant_name
        umDetail?.unit_value = itemVariantUom.unit_value
        umDetail?.conversion_factor = itemVariantUom.conversion_factor
        umDetail?.description = itemVariantUom.description
        umDetail?.uom = umId
        return itemVariantUomRepository.save(umDetail!!)
    }

}