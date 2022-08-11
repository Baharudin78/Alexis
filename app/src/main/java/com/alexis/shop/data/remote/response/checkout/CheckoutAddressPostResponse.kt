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
	@field:SerializedName("address")
	val address: CheckoutAddress? = null
)

data class CheckoutAddress(

	@field:SerializedName("type_address")
	val typeAddress: String? = null,

	@field:SerializedName("recipient_phone_number")
	val recipientPhoneNumber: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("as_dropship")
	val asDropship: Boolean? = null,

	@field:SerializedName("recipient_name")
	val recipientName: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: Int? = null,

	@field:SerializedName("is_default")
	val isDefault: Boolean? = null,

	@field:SerializedName("other_detail")
	val otherDetail: String? = null
)
