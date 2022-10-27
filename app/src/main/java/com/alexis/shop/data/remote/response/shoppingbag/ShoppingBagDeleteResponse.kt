package com.alexis.shop.data.remote.response.shoppingbag

import com.google.gson.annotations.SerializedName

data class ShoppingBagDeleteResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ShoppingBagDeleteResponseData? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ShoppingBagDeleteResponseData(

	@field:SerializedName("message")
	val message: String? = null
)
