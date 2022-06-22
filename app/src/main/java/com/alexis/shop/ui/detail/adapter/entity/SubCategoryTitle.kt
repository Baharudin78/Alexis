package com.alexis.shop.ui.detail.adapter.entity

import com.alexis.shop.ui.detail.adapter.viewholder.SubCategoryTitleVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory

data class SubCategoryTitle(
        val title: String = "",
        val layoutHeight: Int = 0,
        val bonusMarginTop: Boolean = false
) : BaseItemModel() {

    override fun type(typeFactory: BaseItemTypeFactory): Int {
        typeVH = SubCategoryTitleVH.LAYOUT
        return typeFactory.type(this)
    }

}