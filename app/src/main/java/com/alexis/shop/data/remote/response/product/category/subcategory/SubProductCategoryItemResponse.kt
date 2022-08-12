package com.alexis.shop.data.remote.response.product.category.subcategory

import com.google.gson.annotations.SerializedName

data class SubProductCategoryItemResponse(
    @field:SerializedName("merchandise_name")
    val merchandiseName : String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)