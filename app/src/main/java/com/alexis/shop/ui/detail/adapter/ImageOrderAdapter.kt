package com.alexis.shop.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.domain.model.product.ProductsGetByIdImagesModel
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.loadImage
import com.alexis.shop.utils.visible

/**
 * Created by Uwais Alqadri on July 08, 2021
 */
class ImageOrderAdapter(
    private val items: ArrayList<ProductsGetByIdImagesModel>,
    private val onClick: (ProductsGetByIdImagesModel) -> Unit
) : RecyclerView.Adapter<ImageOrderAdapter.ImageOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageOrderViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageOrderViewHolder, position: Int) {
        val item: ProductsGetByIdImagesModel = items[position]

        val selected = when (position) {
            (items.size - 1) -> true
            else -> false
        }

        holder.bind(item, selected)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class ImageOrderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_image_only, parent, false)) {
        var img: ImageView = itemView.findViewById(R.id.image_item)
        var base: RelativeLayout = itemView.findViewById(R.id.base_layout)
        var blokReturned: View = itemView.findViewById(R.id.is_returned)

        fun bind(item: ProductsGetByIdImagesModel, selected: Boolean) {
            img.loadImage(item.image)

            when (selected) {
                true -> blokReturned.visible()
                else -> blokReturned.gone()
            }
			base.background = null
//            if (item.scaled) {
//                base.background =
//                    ContextCompat.getDrawable(context, R.drawable.rectangle_choosed_border)
//            } else {
//                base.background = null
//            }
        }
    }
}