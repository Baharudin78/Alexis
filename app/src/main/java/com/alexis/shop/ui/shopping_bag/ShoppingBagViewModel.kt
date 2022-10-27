package com.alexis.shop.ui.shopping_bag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingBagViewModel @Inject constructor(
    private val wishlistUseCase: WishlistUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    fun getShoppingCart() = shoppingBagUseCase.getShoppingBag().asLiveData()

    fun postWishlist(productItemCode: String) = wishlistUseCase.postWishlist(productItemCode).asLiveData()

    fun deleteShoppingBag(cartId: Int) = shoppingBagUseCase.deleteShoppingBag(cartId).asLiveData()

    fun isUserAuthenticated() = authUseCase.isUserLogin()
}