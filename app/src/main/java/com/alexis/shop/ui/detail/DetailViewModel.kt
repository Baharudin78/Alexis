package com.alexis.shop.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.product.ProductUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val wishlistUseCase: WishlistUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    fun getProductById(productId: Int) = productUseCase.getProductById(productId).asLiveData()

    fun postWishlist(customerId: String, productItemCode: String) =
        wishlistUseCase.postWishlist(customerId, productItemCode).asLiveData()

    fun postShoppingBag(productId: String, quantity: Int, sizeId: String) =
        shoppingBagUseCase.postShoppingBag(productId, authUseCase.getUserId(), quantity, sizeId)
            .asLiveData()
}