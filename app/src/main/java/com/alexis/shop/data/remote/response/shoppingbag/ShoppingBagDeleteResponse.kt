package com.alexis.shop.data.remote.response.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShoppingBagDeleteResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: ShoppingBagDeleteResponseData? = null,

	@SerializedName("error")
	val error: Any? = null,

	@SerializedName("status")
	val status: String? = null
)

data class ShoppingBagDeleteResponseData(

	@SerializedName("message")
	val message: String? = null
)
