package com.alexis.shop.ui.detail.adapter

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.databinding.ListItemPagerBinding
import com.alexis.shop.utils.loadTouchImage
import com.alexis.shop.utils.log
import com.alexis.shop.utils.toast
import com.ortiz.touchview.TouchImageView
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 16, 2021
 */
class ImageViewPagerItem(
	val context: Context,
	val image: ImageModel
) : BindableItem<ListItemPagerBinding>() {

	override fun bind(viewBinding: ListItemPagerBinding, position: Int) {
		viewBinding.apply {
			imgProduct.apply {
				Log.d("IMAGESSS", image.image)
				loadTouchImage(image.image) {
					minZoom = TouchImageView.AUTOMATIC_MIN_ZOOM
				}

				setOnClickListener {
					resetZoomAnimated()
					downloadView.isVisible = false
				}

				if (isZoomed) log("isZoomed $isZoomed")

				setOnLongClickListener {
					log("onLongClicked")
					downloadView.isVisible = true
					true
				}
			}

			btnDownload.setOnClickListener {
				context.toast("Donwloading...")
			}

			parent.setOnClickListener {
				log("clicked")
			}
		}
	}

	override fun getLayout(): Int = R.layout.list_item_pager

	override fun initializeViewBinding(view: View): ListItemPagerBinding {
		return ListItemPagerBinding.bind(view)
	}
}