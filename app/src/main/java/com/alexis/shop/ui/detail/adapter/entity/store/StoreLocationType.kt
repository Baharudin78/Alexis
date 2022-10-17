package com.alexis.shop.ui.detail.adapter.entity.store

import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.ui.detail.adapter.viewholder.LocationTypeVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory

data class StoreLocationType(
    val locationHome : List<AllStoreItemModel>,
    val layoutHeight : Int = 0,
    val layoutWidth : Int = 0
) : BaseItemModel() {
    override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
        typeVH = LocationTypeVH.LAYOUT
        return typeFactoryBase.type(this)
    }

}
