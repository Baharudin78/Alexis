package com.alexis.shop.data.remote.product.category

import com.google.gson.annotations.SerializedName

data class ProductCategoryItemResponse(

    @field:SerializedName("name_in_eng")
    val nameInEng: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("name_in_id")
    val nameInId: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)