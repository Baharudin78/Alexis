package com.alexis.shop.ui.detail.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import com.alexis.shop.R
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTitle
import com.dizcoding.mylibrv.AbstractViewHolder
import kotlinx.android.synthetic.main.item_dashboard_title.view.*

class SubCategoryTitleVH(itemView: View): AbstractViewHolder<SubCategoryTitle>(itemView){
    private val title = itemView.title
    private val layoutItem = itemView.base_title

    companion object{
        val LAYOUT = R.layout.item_dashboard_title
    }

    override fun bind(element: SubCategoryTitle) {
        title.text = element.title
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                element.layoutHeight * 3)
        if (element.bonusMarginTop) {
            layoutParams.topMargin = 200
        }
        layoutItem.layoutParams = layoutParams
    }

}