package com.alexis.shop.data.remote.response.product.size

import com.alexis.shop.domain.model.product.size.ProductSizeModel
import com.google.gson.annotations.SerializedName

data class ProductSizeResponse(
    val code: Int? = null,
    val `data`: ProductSizeItems? = null,
    val status: String? = null
)

data class ProductSizeItems(
    @SerializedName("items")
    val items: List<ProductSize?>
)

data class ProductSize(
    val id: Int? = null,
    val name: String? = null
)

fun ProductSize.toProductSizeModel() : ProductSizeModel{
    return ProductSizeModel(
        id,
        name
    )
}