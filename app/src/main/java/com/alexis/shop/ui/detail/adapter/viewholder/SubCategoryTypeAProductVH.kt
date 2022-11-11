package com.alexis.shop.ui.detail.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.alexis.shop.R
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTypeAProduct
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.dizcoding.mylibrv.AbstractViewHolder
import com.dizcoding.mylibrv.BaseListAdapter
import kotlinx.android.synthetic.main.item_recycle_only.view.*

class SubCategoryTypeAProductVH(itemView: View) :
    AbstractViewHolder<SubCategoryTypeAProduct>(itemView) {
    private val list = itemView.list_item

    companion object {
        val LAYOUT = R.layout.item_recycle_only
    }

    override fun bind(element: SubCategoryTypeAProduct) {
        val adapter = BaseListAdapter(ItemTypeFactoryImpl())
        list.layoutManager = GridLayoutManager(itemView.context, 2)
     //   list.addItemDecoration(LayoutMarginDecoration(2, 15))
        list.adapter = adapter

        element.product.forEach { item ->
            val subCategoryProduct = SubCategoryProduct(
                item, element.layoutHeight, element.layoutWidth
            )
            adapter.addItem(subCategoryProduct)
        }
    }
}