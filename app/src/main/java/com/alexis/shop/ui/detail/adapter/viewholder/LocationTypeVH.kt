package com.alexis.shop.ui.detail.adapter.viewholder

import android.view.View
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.ui.detail.adapter.entity.store.LocationStore
import com.alexis.shop.ui.detail.adapter.entity.store.StoreLocationType
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.dizcoding.mylibrv.AbstractViewHolder
import com.dizcoding.mylibrv.BaseListAdapter
import kotlinx.android.synthetic.main.item_recycle_only.view.*
import kotlinx.android.synthetic.main.item_recycle_only_loc.view.*

class LocationTypeVH(itemView : View) :
    AbstractViewHolder<StoreLocationType>(itemView){
    private val listLoc = itemView.list_loc
    companion object{
        val LAYOUT = R.layout.item_recycle_only_loc
    }

    override fun bind(element: StoreLocationType) {
        val adapterLoc = BaseListAdapter(ItemTypeFactoryImpl())
        listLoc.layoutManager = LinearLayoutManager(itemView.context)
        listLoc.adapter = adapterLoc

        element.locationHome.forEach { item ->
            val loc = LocationStore(
                item, element.layoutHeight, element.layoutWidth
            )
            adapterLoc.addItem(loc)
        }
    }


}