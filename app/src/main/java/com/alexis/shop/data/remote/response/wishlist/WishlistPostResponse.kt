package com.alexis.shop.data.remote.response.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistPostResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: DataPostWishlist? = null,

	@SerializedName("error")
	val error: Any? = null,

	@SerializedName("status")
	val status: String? = null
)

data class DataPostWishlist(

	@SerializedName("item")
	val postWishList: PostWishlist? = null
)

data class PostWishlist(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("customer_id")
	val customeId : String? = null,
	@SerializedName("product_item_code")
	val productItemCode : String? = null
)
