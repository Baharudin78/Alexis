package com.alexis.shop.domain.model.product.category

data class ProductCategoryNewModel(
    val category: String,
    val icon: String,
    val sub_category: List<SubCategoryModel>,
    var isExpandable : Boolean = false

)

data class SubCategoryModel(
    val id: Int,
    val merchandise_name: String
)