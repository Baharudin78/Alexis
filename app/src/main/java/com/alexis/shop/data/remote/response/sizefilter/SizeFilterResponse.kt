package com.alexis.shop.data.remote.response.sizefilter

import com.google.gson.annotations.SerializedName

data class SizeFilterResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: SizesResponse? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class SizesResponse (
	@field:SerializedName("items")
	val sizes: List<SizesItemResponse>? = null,
)

data class SizesItemResponse(

	@field:SerializedName("selection")
	val selection: String? = null,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

//	@field:SerializedName("created_at")
//	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
