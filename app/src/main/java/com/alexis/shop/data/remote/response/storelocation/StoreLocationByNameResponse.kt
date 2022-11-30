package com.alexis.shop.data.remote.response.storelocation

import com.google.gson.annotations.SerializedName

data class StoreLocationByNameResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: StoreLocationData? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class StoreLocationItem(

	@SerializedName("province")
	val province: String? = null,

	@SerializedName("updated_at")
	val updatedAt: String? = null,

	@SerializedName("city")
	val city: String? = null,

	@SerializedName("latitude")
	val latitude: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("open_time")
	val openTime: String? = null,

	@SerializedName("created_at")
	val createdAt: String? = null,

	@SerializedName("phone_number")
	val phoneNumber: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("close_time")
	val closeTime: String? = null,

	@SerializedName("longitude")
	val longitude: String? = null
)

data class StoreLocationData(

	@SerializedName("message")
	val message: String? = null,

	@SerializedName("items")
	val storeLocation: List<StoreLocationItem>? = null
)
