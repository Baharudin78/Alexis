package com.alexis.shop.data.remote.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistGetResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataGetWishlist? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataGetWishlist(

	@field:SerializedName("wishlist")
	val wishlist: List<WishlistItem>? = null
)

data class WishlistItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("wishlist_id")
	val wishlistId: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("indonesia_name")
	val indonesiaName: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null
)
