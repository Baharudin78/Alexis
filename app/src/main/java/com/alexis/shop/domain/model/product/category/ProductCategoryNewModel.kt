package com.alexis.shop.domain.model.product.category

data class ProductCategoryNewModel(
    val category: String,
    val icon: String,
    val sub_category: List<SubCategoryModel>
)

data class SubCategoryModel(
    val id: Int,
    val merchandise_name: String
)