package com.alexis.shop.data.remote.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShoppingBagPostResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ShoppingBagPostData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ShoppingBagPostData(

	@field:SerializedName("product_id")
	val productId: String? = null,

	@field:SerializedName("size_id")
	val sizeId: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null
)
