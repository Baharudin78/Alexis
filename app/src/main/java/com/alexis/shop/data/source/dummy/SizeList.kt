package com.alexis.shop.data.source.dummy

import com.alexis.shop.domain.model.product.SizeModel

/**
 * Created by Uwais Alqadri on July 06, 2021
 */
object SizeList {
	fun getListSize(): ArrayList<SizeModel> {
		return arrayListOf(
			SizeModel("XS", false),
			SizeModel("S", false),
			SizeModel("M", false),
			SizeModel("L", false),
			SizeModel("XL", false)
		)
	}
}