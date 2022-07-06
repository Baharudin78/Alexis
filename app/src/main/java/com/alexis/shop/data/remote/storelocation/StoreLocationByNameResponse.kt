package com.alexis.shop.data.remote.storelocation

import com.google.gson.annotations.SerializedName

data class StoreLocationByNameResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: StoreLocationData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class StoreLocationItem(

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("open_time")
	val openTime: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("close_time")
	val closeTime: String? = null,

	@field:SerializedName("longitude")
	val longitude: Int? = null
)

data class StoreLocationData(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("items")
	val storeLocation: List<StoreLocationItem>? = null
)
