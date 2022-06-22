package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistInteractor @Inject constructor(private val repository: IWishlistRepository): WishlistUseCase {
    override fun postWishlist(productDetailCode: String, userId: Int): Flow<Resource<String>> {
        return repository.postWishlist(productDetailCode, userId)
    }

    override fun getWishlist(userId: Int): Flow<Resource<List<WishlistModel>>> {
        return repository.getWishlist(userId)
    }
}