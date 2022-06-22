package com.alexis.shop.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val useCase: WishlistUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun postWishlist(productDetailCode: String) =
        useCase.postWishlist(productDetailCode, authUseCase.getUserId()).asLiveData()

    fun postShoppingBag(productDetailCode: String, quantity: Int, sizeId: String) =
        shoppingBagUseCase.postShoppingBag(productDetailCode, authUseCase.getUserId(), quantity, sizeId).asLiveData()

    fun getWishlist() = useCase.getWishlist(authUseCase.getUserId()).asLiveData()
}