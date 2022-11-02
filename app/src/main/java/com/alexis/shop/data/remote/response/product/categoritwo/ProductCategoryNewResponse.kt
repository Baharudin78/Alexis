package com.alexis.shop.data.remote.response.product.categoritwo

data class ProductCategoryNewResponse(
    val code: Int,
    val `data`: Data,
    val error: Any,
    val status: String
)

data class Data(
    val items: List<ProductCategoryItem>
)

data class ProductCategoryItem(
    val category: String,
    val icon: String,
    val sub_category: List<SubCategoryItem>,
    var isExpandable : Boolean = false
)

data class SubCategoryItem(
    val id: Int,
    val merchandise_name: String
)