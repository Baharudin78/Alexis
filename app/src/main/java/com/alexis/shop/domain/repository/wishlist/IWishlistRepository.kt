package com.alexis.shop.domain.repository.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface IWishlistRepository {
    fun postWishlist(token: String,customerId: String, productItemCode: String): Flow<Resource<String>>
    fun getWishlist(token : String): Flow<Resource<List<WishlistModel>>>
}