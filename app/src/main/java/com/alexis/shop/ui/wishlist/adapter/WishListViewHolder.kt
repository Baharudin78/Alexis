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
        m_name.text = item.englishName
        m_price.text = itemView.context.getString(R.string.price, item.price.toString())
        m_count.text = m_count.context.getString(R.string.quantity, item.qty.toString())
        m_code.text = item.productId.toString()
        m_weight.text = itemView.context.getString(R.string.weight, item.weight.toString())
        Glide.with(m_image.context)
            .load(item.imageUrl)
            .fitCenter()
            .into(m_image)
    }
}