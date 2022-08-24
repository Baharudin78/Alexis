package com.alexis.shop.data.remote.response.product.category

import com.google.gson.annotations.SerializedName

data class ProductCategoryItemResponse(

    @field:SerializedName("name_in_eng")
    val category: String? = null,

    @field:SerializedName("icon")
    val icon : String? = null,


)

