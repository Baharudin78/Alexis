package com.alexis.shop.data.remote.response.product.category

import com.google.gson.annotations.SerializedName

data class ProductCategoryItemResponse(

//    @field:SerializedName("name_in_eng")
//    val nameInEng: String? = null,
//
//    @field:SerializedName("updated_at")
//    val updatedAt: String? = null,

    @field:SerializedName("name_in_eng")
    val category: String? = null,

    @field:SerializedName("icon")
    val icon : String? = null,


)

