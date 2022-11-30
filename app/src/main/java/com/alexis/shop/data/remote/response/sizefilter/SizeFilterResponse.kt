package com.alexis.shop.data.remote.response.sizefilter

import com.google.gson.annotations.SerializedName

data class SizeFilterResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: SizesResponse? = null,

	@SerializedName("error")
	val error: String? = null,

	@SerializedName("status")
	val status: String? = null
)

data class SizesResponse (
	@SerializedName("items")
	val sizes: List<SizesItemResponse>? = null,
)

data class SizesItemResponse(

	@SerializedName("selection")
	val selection: String? = null,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String? = null,

	@SerializedName("name")
	val name: String? = null,

//	@field:SerializedName("created_at")
//	val createdAt: String? = null,

	@SerializedName("id")
	val id: String? = null
)
