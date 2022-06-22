package com.alexis.shop.ui.shopping_bag.adapter

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
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.loadImage
import com.alexis.shop.utils.visible

/**
 * Created by Uwais Alqadri on August 04, 2021
 */
class ImageOrderButBiggerAdapter(private val context: Context,
						private val items: ArrayList<ImageModel>,
						private val onClick: (ImageModel) -> Unit
) : RecyclerView.Adapter<ImageOrderButBiggerAdapter.ImageOrderButBiggerViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageOrderButBiggerViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return ImageOrderButBiggerViewHolder(inflater, parent)
	}

	override fun onBindViewHolder(holder: ImageOrderButBiggerViewHolder, position: Int) {
		val item: ImageModel = items[position]

		val selected = when (position) {
			(items.size - 1) -> true
			else -> false
		}

		holder.bind(context, item, selected)
		holder.itemView.setOnClickListener {
			onClick(item)
		}
	}

	override fun getItemCount(): Int = items.size

	class ImageOrderButBiggerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
		RecyclerView.ViewHolder(inflater.inflate(R.layout.item_image_only_but_bigger, parent, false)) {
		var img: ImageView = itemView.findViewById(R.id.image_item)
		var base: RelativeLayout = itemView.findViewById(R.id.base_layout)
		var blokReturned: View = itemView.findViewById(R.id.is_returned)

		fun bind(context: Context, item: ImageModel, selected: Boolean) {
			img.loadImage(item.image)

			when(selected){
				true -> blokReturned.visible()
				else -> blokReturned.gone()
			}

			if (item.scaled) {
				base.background = ContextCompat.getDrawable(context, R.drawable.rectangle_choosed_border)
			} else {
				base.background = null
			}
		}
	}
}