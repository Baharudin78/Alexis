package com.alexis.shop.domain.model.shoppingbag

import com.alexis.shop.domain.model.product.ProductsByIdModel
import java.io.Serializable

data class ShoppingBagModel(
    val id : Int? = 0,
    val customerId : Int? = 0,
    val productItemCode : String? = "",
    val productSizeId : String? = "",
    val unit : String? = "",
    val price : Int? = 0,
    val finalPrice : Int? = 0,
    val product : ProductsByIdModel? = null
): Serializable
