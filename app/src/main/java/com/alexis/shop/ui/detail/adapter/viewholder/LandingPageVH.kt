package com.alexis.shop.ui.detail.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.alexis.shop.R
import com.alexis.shop.ui.detail.adapter.entity.LandingPage
import com.alexis.shop.utils.loadImage
import com.bumptech.glide.Glide
import com.dizcoding.mylibrv.AbstractViewHolder
import kotlinx.android.synthetic.main.item_dashboard_imagelanding.view.*

class LandingPageVH(itemView: View): AbstractViewHolder<LandingPage>(itemView){
    private val imv = itemView.image_landing
    private val layoutItem = itemView.base_items

    companion object{
        val LAYOUT = R.layout.item_dashboard_imagelanding
    }

    override fun bind(element: LandingPage) {
        val linParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                element.layoutHeight)
        imv.scaleType = ImageView.ScaleType.CENTER_CROP
        imv.setImageResource(R.drawable.cs)
        layoutItem.layoutParams = linParams
    }

}