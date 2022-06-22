package com.alexis.shop.domain.model.wishlist

import java.io.Serializable

data class WishlistModel(
    val updatedAt: String = "",
    val productId: Int = 0,
    val createdAt: String = "",
    val id: Int = 0,
    val customerId: Int = 0,
    val indonesiaName: String = "",
    val englishName: String = "",
    val price: Int = 0,
    val weight: Int = 0,
    val qty: Int = 0,
    val imageUrl: String = ""
) : Serializable
