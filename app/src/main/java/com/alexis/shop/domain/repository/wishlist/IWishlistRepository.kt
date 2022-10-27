package com.alexis.shop.domain.repository.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface IWishlistRepository {
    fun postWishlist(productItemCode: String): Flow<Resource<String>>
    fun getWishlist(): Flow<Resource<List<WishlistModel>>>
}