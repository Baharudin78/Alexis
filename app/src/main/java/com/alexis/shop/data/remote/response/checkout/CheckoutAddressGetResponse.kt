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

data class CheckoutAddressItem(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("as_dropship")
	val asDropship: Int? = null,

	@field:SerializedName("is_default")
	val isDefault: Int? = null,

	@field:SerializedName("type_address")
	val typeAddress: String? = null,

	@field:SerializedName("recipient_phone_number")
	val recipientPhoneNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("recipient_name")
	val recipientName: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: Int? = null,

	@field:SerializedName("other_detail")
	val otherDetail: String? = null,
)

data class CheckoutAddressData(

	@field:SerializedName("items")
	val address: List<CheckoutAddressItem>? = null
)
