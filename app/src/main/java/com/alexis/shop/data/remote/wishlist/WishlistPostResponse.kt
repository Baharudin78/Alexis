package com.alexis.shop.data.remote.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistPostResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataPostWishlist? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataPostWishlist(

	@field:SerializedName("product_id")
	val productId: String? = null
)
