package com.alexis.shop.ui.menu.adapter.item

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.databinding.ItemRecycleOnlyBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 18, 2021
 */
class SocialListItem(
	val context: Context,
	val isOpen: Boolean,
	val isClose: Boolean,
	val image: ArrayList<MenuModel> = ArrayList()
) : BindableItem<ItemRecycleOnlyBinding>() {

	override fun bind(viewBinding: ItemRecycleOnlyBinding, position: Int) {
		viewBinding.apply {
			val itemAdapter = GroupAdapter<GroupieViewHolder>()
			listItem.apply {
				layoutManager = GridLayoutManager(context, 5)
				adapter = itemAdapter
			}

			image.forEach { image ->
				image.isOpen = isOpen
				image.isClose = isClose
				itemAdapter.add(SocialItem(context, image))
			}
		}
	}

	override fun getLayout(): Int = R.layout.item_recycle_only

	override fun initializeViewBinding(view: View): ItemRecycleOnlyBinding {
		return ItemRecycleOnlyBinding.bind(view)
	}
}