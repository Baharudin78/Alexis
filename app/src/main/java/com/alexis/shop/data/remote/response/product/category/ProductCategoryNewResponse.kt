package com.alexis.shop.data.remote.response.product.category

import com.google.gson.annotations.SerializedName

data class ProductCategoryNewResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)

data class Data(
    @SerializedName("items")
    val items: List<ProductCategoryItem>
)

data class ProductCategoryItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("sub_category")
    val sub_category: List<SubCategoryItem>,
    var isExpandable : Boolean
)

data class SubCategoryItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("merchandise_name")
    val merchandise_name: String
)