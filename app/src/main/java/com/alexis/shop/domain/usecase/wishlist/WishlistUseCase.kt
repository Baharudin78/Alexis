package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface WishlistUseCase {
    fun postWishlist(productDetailCode: String, userId: Int): Flow<Resource<String>>
    fun getWishlist(userId: Int): Flow<Resource<List<WishlistModel>>>
}