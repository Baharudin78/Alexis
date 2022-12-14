package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistInteractor @Inject constructor(private val repository: IWishlistRepository): WishlistUseCase {
    override fun postWishlist(productItemCode: String): Flow<Resource<String>> {
        return repository.postWishlist(productItemCode)
    }

    override fun getWishlist(): Flow<Resource<List<WishlistModel>>> {
        return repository.getWishlist()
    }

    override fun deleteWishlist(id: Int): Flow<Resource<MessageResponse>> {
        return repository.deleteWishlist(id)
    }
}