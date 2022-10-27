package com.alexis.shop.data.remote.response.wishlist

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

	@field:SerializedName("items")
	val wishlist: List<WishlistItem>? = null
)

data class WishlistItem(

	//val id : Int? = null,
	val customer_id : Int? = null,
	val product_item_code : String? = null,
	val product : ProductItem? = null,

//	@field:SerializedName("updated_at")
//	val updatedAt: String? = null,
//
//	@field:SerializedName("wishlist_id")
//	val wishlistId: Int? = null,
//
//	@field:SerializedName("product_id")
//	val productId: Int? = null,
//
//	@field:SerializedName("created_at")
//	val createdAt: String? = null,
//
//	@field:SerializedName("indonesia_name")
//	val indonesiaName: String? = null,
//
//	@field:SerializedName("english_name")
//	val englishName: String? = null,
//
//	@field:SerializedName("image_url")
//	val imageUrl: String? = null,
//
//	@field:SerializedName("price")
//	val price: Int? = null,
//
//	@field:SerializedName("weight")
//	val weight: Int? = null,
//
//	@field:SerializedName("qty")
//	val qty: Int? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("customer_id")
//	val customerId: Int? = null
)

data class ProductItem(
	val id : Int? = null,
	val barcode : String? = null,
	val stock_keeping_unit : String? = null,
	val item_code : String? = null,
	val name : String? = null,
	val product_subcategory_id : Int? = null,
	val stock : Int? = null,
	val price : Int? = null,
	val weight : Int? = null,
	val style_code : String? = null,
	val product_material_id : String? = null,
	val color_code : String? = null,
	val product_size_id : String? = null,
	val packaging_id : Int? = null,
	val status : String? = null,
	val change_to_stored : String? = null,
	val change_to_listed : String? = null,
	val change_to_delisted : String? = null,
	val store_location_id : Int? = null,
	val user_id : Int? = null
)
