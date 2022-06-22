package com.alexis.shop.data.remote.storelocation

import com.google.gson.annotations.SerializedName

data class AllStoreLocationResponse(
	@field:SerializedName("data")
	val data: DataStoreLocationResponse? = null,
)

data class DataStoreLocationResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("store_location")
	val storeLocation: List<AllStoreItemResponse>? = null
)

data class AllStoreItemResponse(
	@field:SerializedName("province")
	val province: String? = null
)
