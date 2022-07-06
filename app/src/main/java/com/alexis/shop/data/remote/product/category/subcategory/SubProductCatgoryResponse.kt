package com.alexis.shop.data.remote.product.category.subcategory

import com.alexis.shop.data.remote.product.category.ProductCategoryItemResponse
import com.google.gson.annotations.SerializedName

data class SubProductCategoryResponse(
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: SubProductCategoryData? = null,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class SubProductCategoryData (
    @field:SerializedName("items")
    val productCategory: List<SubProductCategoryItemResponse>? = null,
)