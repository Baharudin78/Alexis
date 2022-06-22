package com.alexis.shop.ui.account.adapter

import android.view.View
import com.alexis.shop.R
import com.alexis.shop.domain.model.faq.TermAndCondition
import com.alexis.shop.databinding.ItemTermAndConditionBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 15, 2021
 */
class TermItemList(
	val item: TermAndCondition
) : BindableItem<ItemTermAndConditionBinding>(){

	override fun bind(viewBinding: ItemTermAndConditionBinding, position: Int) {
		viewBinding.apply {
			title.text = item.title
			body.text = item.body
		}
	}

	override fun getLayout(): Int = R.layout.item_term_and_condition

	override fun initializeViewBinding(view: View): ItemTermAndConditionBinding {
		return ItemTermAndConditionBinding.bind(view)
	}
}