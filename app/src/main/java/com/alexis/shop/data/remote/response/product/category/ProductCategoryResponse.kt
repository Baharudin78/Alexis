package com.alexis.shop.data.remote.response.product.category

import com.google.gson.annotations.SerializedName

data class ProductCategoryResponse(
	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ProductCategoryData? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProductCategoryData (
	@field:SerializedName("items")
	val productCategory: List<ProductCategoryItemResponse>? = null,
)


