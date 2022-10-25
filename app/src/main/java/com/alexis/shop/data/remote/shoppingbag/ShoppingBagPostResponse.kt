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

	@field:SerializedName("item")
	val shopingBagPostData : ShoppingBagPostDataItem? = null,
)

data class ShoppingBagPostDataItem(
	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("customer_id")
	val customerId : Int? = null,
	@field:SerializedName("product_item_code")
	val productItemCode : String? = null,
	@field:SerializedName("product_size_id")
	val productSizeId : String? = null,
	@field:SerializedName("unit")
	val unit : String? = null,
	@field:SerializedName("price")
	val price : Int? = null,
	@field:SerializedName("final_price")
	val finalPrice : Int? = null
)

