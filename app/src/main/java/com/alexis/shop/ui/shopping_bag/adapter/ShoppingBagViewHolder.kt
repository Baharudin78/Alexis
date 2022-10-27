package com.alexis.shop.ui.shopping_bag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.utils.getOneXMeters
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.visible
import com.bumptech.glide.Glide

class ShoppingBagViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_shopping_bag, parent, false)) {
    private var motionlayout: MotionLayout
    private var m_image: ImageView

    private var m_price: TextView
    private var m_name: TextView
    private var m_count: TextView
    private var m_code: TextView
    private var m_size: TextView
    private var m_weight: TextView

    private var b_satu: ImageView
    private var b_dua: ImageView
    private var b_tiga: ImageView

    private var mode_reserve: TextView
    private var mode_itemlayout: ConstraintLayout

    init {
        motionlayout = itemView.findViewById(R.id.parent)
        m_image = itemView.findViewById(R.id.image_item)
        m_price = itemView.findViewById(R.id.item_price)
        m_name = itemView.findViewById(R.id.item_name)
        m_count = itemView.findViewById(R.id.item_count)
        m_code = itemView.findViewById(R.id.item_code)
        m_size = itemView.findViewById(R.id.item_size)
        m_weight = itemView.findViewById(R.id.item_weight)

        b_satu = itemView.findViewById(R.id.btn_1)
        b_dua = itemView.findViewById(R.id.btn_2)
        b_tiga = itemView.findViewById(R.id.btn_3)

        mode_reserve = itemView.findViewById(R.id.txt_reserve)
        mode_itemlayout = itemView.findViewById(R.id.allbackgr)
    }

    fun bind(item: ShoppingBagModel) {
//        when(item){
//            "00Last00" -> {
//                mode_reserve.visible()
//                mode_itemlayout.gone()
//            }
//            else -> {
//                mode_reserve.gone()
//                mode_itemlayout.visible()
//
//                m_name.text = item
//            }
//        }

        mode_reserve.gone()
        mode_itemlayout.visible()

        m_name.text = item.product?.productName
        m_price.text = itemView.context.getString(R.string.price, item.price.toString())
        m_count.text = itemView.context.getString(R.string.quantity, item.product?.stock.toString())
        m_weight.text = itemView.context.getString(R.string.weight, item.product?.weight.toString())
       // m_size.text = itemView.context.getString(R.string.size, item.size)
        m_code.text = item.productItemCode
        Glide.with(itemView.context)
            .load(item.product?.images)
            .fitCenter()
            .into(m_image)

        val satuanX = getOneXMeters(itemView.context)
        val layoutParamsImage = LinearLayout.LayoutParams(satuanX*3, satuanX*4)

        m_image.layoutParams = layoutParamsImage
    }
}