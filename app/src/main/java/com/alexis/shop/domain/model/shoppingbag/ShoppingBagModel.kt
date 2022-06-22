package com.alexis.shop.domain.model.shoppingbag

import java.io.Serializable

data class ShoppingBagModel(
    val productId: String = "",
    val qty: Int = 0,
    val indonesiaName: String = "",
    val englishName: String = "",
    val price: Int = 0,
    val weight: Int = 0,
    val imageUrl: String = "",
    val shoppingBagId: Int = 0,
    val size: String = ""
): Serializable
