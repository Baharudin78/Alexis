package com.alexis.shop.domain.model.product.category

data class ProductCategoryNewModel(
    var data : List<ProductCategoryNewItem> = mutableListOf()
)
data class ProductCategoryNewItem(
    var category: String,
    var icon: String,
    var sub_category: List<SubCategoryModel>,
    var isExpandable : Boolean

)
data class SubCategoryModel(
    var id: Int,
    var merchandise_name: String
)