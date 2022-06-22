package com.alexis.shop.ui.detail.adapter.entity

import com.alexis.shop.domain.model.product.ProductsModel
import com.alexis.shop.ui.detail.adapter.viewholder.SubCategoryTypeAProductVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory

data class SubCategoryTypeAProduct(
    val product: List<ProductsModel>,
    val layoutHeight: Int = 0,
    val layoutWidth: Int = 0
) : BaseItemModel() {

    override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
        typeVH = SubCategoryTypeAProductVH.LAYOUT
        return typeFactoryBase.type(this)
    }

}