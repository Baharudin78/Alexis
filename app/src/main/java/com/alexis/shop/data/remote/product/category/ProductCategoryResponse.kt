package com.alexis.shop.data.remote.product.category

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
	@field:SerializedName("product_category")
	val productCategory: List<ProductCategoryItemResponse>? = null,
)


