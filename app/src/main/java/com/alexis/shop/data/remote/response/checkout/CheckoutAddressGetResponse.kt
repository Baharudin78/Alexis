package com.alexis.shop.data.remote.response.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutAddressGetResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: CheckoutAddressData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CheckoutAddressData(

	@field:SerializedName("items")
	val address: List<CheckoutAddressItem>? = null
)

data class CheckoutAddressItem(

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


