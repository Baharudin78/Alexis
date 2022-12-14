package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import kotlinx.coroutines.flow.Flow

interface WishlistUseCase {
    fun postWishlist(productItemCode: String): Flow<Resource<String>>
    fun getWishlist(): Flow<Resource<List<WishlistModel>>>
    fun deleteWishlist(id : Int) : Flow<Resource<MessageResponse>>
}