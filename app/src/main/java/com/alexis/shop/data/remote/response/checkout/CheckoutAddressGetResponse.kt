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
	@field:SerializedName("id")
	val id : Int? = null,
	@field:SerializedName("customer_id")
	val customerId : Int? = null,
	@field:SerializedName("recipient_name")
	val recipientName : String? = null,
	@field:SerializedName("address")
	val address : String? = null,
	@field:SerializedName("address_2")
	val addressTwo : String? = null,
	@field:SerializedName("village_id")
	val villageId : String? = null,
	@field:SerializedName("latitude")
	val latitude : String? = null,
	@field:SerializedName("longitude")
	val longitude : String? = null,
	@field:SerializedName("postal_code")
	val postalCode : String? = null,
	@field:SerializedName("recipient_phone_number")
	val recipientPhoneNumber : String? = null,
	@field:SerializedName("is_default")
	val isDefault : Int? = null,
	@field:SerializedName("as_dropship")
	val asDropship : Int? = null
)


