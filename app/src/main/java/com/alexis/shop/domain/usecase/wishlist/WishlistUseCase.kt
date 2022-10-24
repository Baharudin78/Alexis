package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface WishlistUseCase {
    fun postWishlist(customerId: String, productItemCode: String): Flow<Resource<String>>
    fun getWishlist(): Flow<Resource<List<WishlistModel>>>
}