package com.alexis.shop.data.remote.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShoppingBagResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataCarts? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CartsItem(

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("indonesian_name")
	val indonesianName: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null
)

data class DataCarts(

	@field:SerializedName("carts")
	val carts: List<CartsItem>? = null
)
