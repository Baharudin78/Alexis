package com.alexis.shop.data.remote.response.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShoppingBagPostResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: ShoppingBagPostData? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class ShoppingBagPostData(

	@SerializedName("item")
	val shopingBagPostData : ShoppingBagPostDataItem? = null,

)

data class ShoppingBagPostDataItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("customer_id")
	val customerId : Int? = null,
	@SerializedName("product_item_code")
	val productItemCode : String? = null,
	@SerializedName("product_size_id")
	val productSizeId : String? = null,
	@SerializedName("unit")
	val unit : String? = null,
	@SerializedName("price")
	val price : Int? = null,
	@SerializedName("final_price")
	val finalPrice : Int? = null
)

