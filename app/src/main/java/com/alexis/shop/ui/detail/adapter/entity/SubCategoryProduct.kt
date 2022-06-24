package com.alexis.shop.ui.detail.adapter.entity

import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.ui.detail.adapter.viewholder.SubCategoryProductVH
import com.dizcoding.mylibrv.*

data class SubCategoryProduct(
    val productModels: ProductBaruModel,
    val layoutHeight: Int = 0,
    val layoutWidth: Int = 0
) : BaseItemModel() {

    override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
        typeVH = SubCategoryProductVH.LAYOUT

        return typeFactoryBase.type(this)
    }

}