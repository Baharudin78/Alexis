package com.alexis.shop.ui.account.adapter

import android.view.View
import com.alexis.shop.R
import com.alexis.shop.domain.model.order.TrackingModel
import com.alexis.shop.databinding.ItemTrackingOrderBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 06, 2021
 */
class TrackingItemList(
	private val tracking: TrackingModel
) : BindableItem<ItemTrackingOrderBinding>() {

	override fun bind(viewBinding: ItemTrackingOrderBinding, position: Int) {
		viewBinding.apply {
			tvTimestamp.text = tracking.date
			tvDescription.text = tracking.desc
		}
	}

	override fun getLayout(): Int = R.layout.item_tracking_order

	override fun initializeViewBinding(view: View): ItemTrackingOrderBinding {
		return ItemTrackingOrderBinding.bind(view)
	}
}