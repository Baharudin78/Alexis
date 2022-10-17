package com.alexis.shop.ui.detail.adapter.factory

import android.view.View
import com.alexis.shop.ui.detail.adapter.viewholder.*
import com.dizcoding.mylibrv.AbstractViewHolder
import com.dizcoding.mylibrv.BaseItemTypeFactory

class ItemTypeFactoryImpl: BaseItemTypeFactory {

    override fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*> {
        return when (type){
            LandingPageVH.LAYOUT -> LandingPageVH(parent)

            SubCategoryTitleVH.LAYOUT -> SubCategoryTitleVH(parent)
            SubCategoryProductVH.LAYOUT -> SubCategoryProductVH(parent)
            SubCategoryTypeAProductVH.LAYOUT -> SubCategoryTypeAProductVH(parent)
            LocationTypeVH.LAYOUT -> LocationTypeVH(parent)


            else -> createViewHolder(parent, type)
        }
    }

}