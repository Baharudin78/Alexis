package com.alexis.shop.domain.usecase.wishlist

import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistInteractor @Inject constructor(private val repository: IWishlistRepository): WishlistUseCase {
    override fun postWishlist(token: String,customerId: String, productItemCode: String): Flow<Resource<String>> {
        return repository.postWishlist(token, customerId, productItemCode)
    }

    override fun getWishlist(token : String): Flow<Resource<List<WishlistModel>>> {
        return repository.getWishlist(token)
    }
}