package com.alexis.shop.data.source.dummy

import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel

/**
 * Created by Uwais Alqadri on July 14, 2021
 */
fun getMenuList(isLogged: Boolean): ArrayList<MenuModel> {
	val dataMenu = ArrayList<MenuModel>()
	var id = 1
	var reverseId = 14
//	dataCategory?.map {
//		it.category
//	}
//	dataCategory?.forEach {
//		when(it.category.lowercase()) {
//			"clothings", "pakaian" -> {
//				dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_cloting, "Clothings"))
//				id++
//				reverseId--
//			}
//			"bags", "tas" -> {
//				dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_bags, "Bags"))
//				id++
//				reverseId--
//			}
//			"shoes", "sepatu" -> {
//				dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_shoes, "Shoes"))
//				id++
//				reverseId--
//			}
//		}
//	}
	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_filter,"Size Filter"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, 0,"-"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_aboutus,"About Us"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_store_location,"Store Location"))
	id++
	reverseId--

	if (isLogged) {
		dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_people, "My Account"))
	} else {
		dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_people, "Login/Register"))
	}
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_help_center,"Help Center"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_referen_earn,"Refer & Earn"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, reverseId, R.drawable.ic_contactus,"Contact Us"))
	id++
	reverseId--

	dataMenu.add(MenuModel(id, 1, R.drawable.ic_instagram, "Instagram"))
	id++

	dataMenu.add(MenuModel(id, 2, R.drawable.ic_facebook, "Facebook"))
	id++

	dataMenu.add(MenuModel(id, 3, R.drawable.ic_youtube, "Youtube"))
	id++

	dataMenu.add(MenuModel(id, 4, R.drawable.ic_tiktok, "Tiktok"))
	id++

	dataMenu.add(MenuModel(id, 5, R.drawable.ic_playstore, "Play Store"))
	return dataMenu
}





