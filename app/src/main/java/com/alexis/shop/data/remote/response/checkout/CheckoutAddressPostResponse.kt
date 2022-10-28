package com.alexis.shop.data.remote.response.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutAddressPostResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: CheckoutAddressPostData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CheckoutAddressPostData(
	@field:SerializedName("item")
	val address: CheckoutAddress? = null
)

data class CheckoutAddress(

	val id : Int? = null,
	val customerId : Int? = null,
	val recipientName : String? = null,
	val address : String? = null,
	val addressTwo : String? = null,
	val villageId : String? = null,
	val latitude : String? = null,
	val longitude : String? = null,
	val postalCode : String? = null,
	val recipientPhoneNumber : String? = null,
	val isDefault : String? = null,
	val asDropship : String? = null
)
