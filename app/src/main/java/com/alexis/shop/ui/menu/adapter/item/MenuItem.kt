package com.alexis.shop.ui.menu.adapter.item

import android.content.Intent
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.databinding.ItemMenuMainBinding
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import com.alexis.shop.ui.detail.SubCategoryPageActivity
import com.alexis.shop.ui.menu.adapter.SubMenuAdapter
import com.alexis.shop.utils.*
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on July 18, 2021
 */
class MenuItem(
	val fragment: Fragment,
	val item: MenuModel,
	val onClick: (MenuModel) -> Unit
) : BindableItem<ItemMenuMainBinding>() {

	override fun bind(viewBinding: ItemMenuMainBinding, position: Int) {
		viewBinding.apply {
			val lSubmenu: ArrayList<SubCategoryModel> = ArrayList()
			if(item.icon == 0) {
				sparateLine.visible()
				textMenu.gone()
			} else {
				sparateLine.gone()
				textMenu.setCompoundDrawablesWithIntrinsicBounds(item.icon, 0, 0, 0)
				textMenu.text = item.title
			}

			when(item.title) {
				"Clothings" -> {
//					lSubmenu.add("Tops 60k")
//					lSubmenu.add("Dresses 60k")
//					lSubmenu.add("Knitwear 60k")
//					lSubmenu.add("Skirt 60k")
//					lSubmenu.add("Pants 80k")
//					lSubmenu.add("Jeans 80k")
//					lSubmenu.add("Blazer 80k")
//					lSubmenu.add("Knitted Sweater 80k")
//					lSubmenu.add("Sweatshirt 80k")
//					lSubmenu.add("Jacket 80k")
				}
				"Bags" -> {
//					lSubmenu.add("Bags")
//					lSubmenu.add("Bags 60k")
//					lSubmenu.add("Bags 80k")
//					lSubmenu.add("Bags 100k")
				}
				"Shoes" -> {
//					lSubmenu.add("Shoes")
//					lSubmenu.add("Shoes 60k")
//					lSubmenu.add("Shoes 80k")
//					lSubmenu.add("Shoes 100k")
				}
			}

			recycle.apply {
				layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
				adapter = SubMenuAdapter(lSubmenu) {
					val intent = Intent(context, SubCategoryPageActivity::class.java)
					intent.putExtra("category", it.merchandiseName)
					context.startActivity(intent)
					fragment.justOut()
				}
			}

			recycle.isVisible = item.isChoosed

			root.setOnClickListener {
				onClick(item)
			}

			if (item.isOpen) root.animateOpen(item.id)
			else if (item.isClose) root.animateClose(item.reverseId)
		}
	}

	override fun getLayout(): Int = R.layout.item_menu_main

	override fun initializeViewBinding(view: View): ItemMenuMainBinding {
		return ItemMenuMainBinding.bind(view)
	}

}