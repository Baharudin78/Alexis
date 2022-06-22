package com.alexis.shop.data.source.dummy

import com.alexis.shop.domain.model.product.ImageModel

/**
 * Created by Uwais Alqadri on July 05, 2021
 */
object ImageList {
	fun getImageProduct(scaled: Boolean): ArrayList<ImageModel> {
		return arrayListOf(
			ImageModel(
				"https://cdn.tobi.com/product_images/sm/2/grey-ayda-cowl-neck-sweater-dress.jpg",
				scaled
			),
			ImageModel(
				"http://api.myalexis.xyz:3001/uploads/product_image/234234BA-AYAUB-B1-S1.jpg",
				scaled
			),
			ImageModel(
				"http://api.myalexis.xyz:3001/uploads/product_image/234234BB-AYAUB-B1-S1.jpg",
				scaled
			),
			ImageModel(
				"http://api.myalexis.xyz:3001/uploads/product_image/234234BC-AYAUB-B1-S1.jpg",
				scaled
			),
			ImageModel(
				"http://api.myalexis.xyz:3001/uploads/product_image/234234BD-AYAUB-B1-S1.jpg",
				scaled
			)
		)
	}
}