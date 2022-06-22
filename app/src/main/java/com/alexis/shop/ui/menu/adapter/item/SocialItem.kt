package com.alexis.shop.ui.menu.adapter.item

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.databinding.ItemMenuSosmedButtonBinding
import com.alexis.shop.utils.*
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 18, 2021
 */
class SocialItem(
	val context: Context,
	val item: MenuModel
) : BindableItem<ItemMenuSosmedButtonBinding>() {

	override fun bind(viewBinding: ItemMenuSosmedButtonBinding, position: Int) {
		viewBinding.apply {
			val linParams = LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT)

			baseItems.layoutParams = linParams
			imageLanding.setImageDrawable(context.getDrawable(item.icon))

			if (item.isOpen) root.animateOpen(item.id)
			else if (item.isClose) root.animateClose(item.reverseId)
		}
	}

	override fun getLayout(): Int = R.layout.item_menu_sosmed_button

	override fun initializeViewBinding(view: View): ItemMenuSosmedButtonBinding {
		return ItemMenuSosmedButtonBinding.bind(view)
	}
}