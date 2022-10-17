package com.alexis.shop.ui.wishlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.utils.gone
import com.bumptech.glide.Glide

class WishListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_wish_list, parent, false)) {
    private var m_image: ImageView = itemView.findViewById(R.id.image_item)
    private var m_price: TextView = itemView.findViewById(R.id.item_price)
    private var m_name: TextView = itemView.findViewById(R.id.item_name)
    private var m_count: TextView = itemView.findViewById(R.id.item_count)
    private var m_code: TextView = itemView.findViewById(R.id.item_code)
    private var m_weight: TextView = itemView.findViewById(R.id.item_weight)

    fun bind(item: WishlistModel) {
        m_name.text = item.product?.name
        m_price.text = item.product?.price.toString()
        m_count.text = item.product?.stock.toString()
        m_code.text = item.product?.id.toString()
        m_weight.text = item.product?.weight.toString()
//        Glide.with(m_image.context)
//            .load(item.product.)
//            .fitCenter()
//            .into(m_image)
    }
    //itemView.context.getString(R.string.price, item.product.price)
    //m_count.context.getString(R.string.quantity, item.product.stock)
    //itemView.context.getString(R.string.weight, item.weight.toString())
}