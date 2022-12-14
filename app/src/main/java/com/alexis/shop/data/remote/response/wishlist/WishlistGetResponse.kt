package com.alexis.shop.data.remote.response.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistGetResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: DataGetWishlist? = null,

	@SerializedName("error")
	val error: Any? = null,

	@SerializedName("status")
	val status: String? = null
)

data class DataGetWishlist(

	@SerializedName("items")
	val wishlist: List<WishlistItem>? = null
)

data class WishlistItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("customer_id")
	val customer_id : Int? = null,
	@SerializedName("product_item_code")
	val product_item_code : String? = null,
	@SerializedName("product")
	val product : ProductItem? = null,
)

data class ProductItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("barcode")
	val barcode : String? = null,
	@SerializedName("stock_keeping_unit")
	val stock_keeping_unit : String? = null,
	@SerializedName("item_code")
	val item_code : String? = null,
	@SerializedName("name")
	val name : String? = null,
	@SerializedName("product_subcategory_id")
	val product_subcategory_id : Int? = null,
	@SerializedName("stock")
	val stock : Int? = null,
	@SerializedName("price")
	val price : Int? = null,
	@SerializedName("weight")
	val weight : Int? = null,
	@SerializedName("style_code")
	val style_code : String? = null,
	@SerializedName("product_material_id")
	val product_material_id : String? = null,
	@SerializedName("color_code")
	val color_code : String? = null,
	@SerializedName("product_size_id")
	val product_size_id : String? = null,
	@SerializedName("packaging_id")
	val packaging_id : Int? = null,
	@SerializedName("status")
	val status : String? = null,
	@SerializedName("change_to_stored")
	val change_to_stored : String? = null,
	@SerializedName("change_to_listed")
	val change_to_listed : String? = null,
	@SerializedName("change_to_delisted")
	val change_to_delisted : String? = null,
	@SerializedName("store_location_id")
	val store_location_id : Int? = null,
	@SerializedName("user_id")
	val user_id : Int? = null
)
