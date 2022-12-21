package com.alexis.shop.domain.model.product.size


data class ProductSizeListModel(
    val size : List<ProductSizeModel> = mutableListOf()
)
data class ProductSizeModel(
    val id: Int? = null,
    val name: String? = null,
    var isSelected : Boolean = false
)
