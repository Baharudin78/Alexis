package com.alexis.shop.data.remote.response.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutAddressPostResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: CheckoutAddressPostData? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class CheckoutAddressPostData(
	@SerializedName("item")
	val address: CheckoutAddress? = null
)

data class CheckoutAddress(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("customer_id")
	val customerId : Int? = null,
	@SerializedName("recipient_name")
	val recipientName : String? = null,
	@SerializedName("address")
	val address : String? = null,
	@SerializedName("address_two")
	val addressTwo : String? = null,
	@SerializedName("village_id")
	val villageId : String? = null,
	@SerializedName("latitude")
	val latitude : String? = null,
	@SerializedName("longitude")
	val longitude : String? = null,
	@SerializedName("postal_code")
	val postalCode : String? = null,
	@SerializedName("recipient_phone_number")
	val recipientPhoneNumber : String? = null,
	@SerializedName("is_default")
	val isDefault : String? = null,
	@SerializedName("as_dropship")
	val asDropship : String? = null
)
