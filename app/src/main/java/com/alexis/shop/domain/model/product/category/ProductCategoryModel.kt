package com.alexis.shop.domain.model.product.category

data class ProductCategoryModelList(
    var data : List<ProductCategoryModel> = mutableListOf()
)
data class ProductCategoryModel (
   // val nameInEng: String = "",
    val category: String = "",
    //val merchandiseName : String = "",
    val icon : String = "",
   // val id: Int = 0
)