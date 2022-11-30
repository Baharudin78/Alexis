package com.alexis.shop.data.remote.response.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutAddressGetResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: CheckoutAddressData? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class CheckoutAddressData(

	@SerializedName("items")
	val address: List<CheckoutAddressItem>? = null
)

data class CheckoutAddressItem(
	@SerializedName("id")
	val id : Int? = null,
	@SerializedName("customer_id")
	val customerId : Int? = null,
	@SerializedName("recipient_name")
	val recipientName : String? = null,
	@SerializedName("address")
	val address : String? = null,
	@SerializedName("address_2")
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
	val isDefault : Int? = null,
	@SerializedName("as_dropship")
	val asDropship : Int? = null
)


