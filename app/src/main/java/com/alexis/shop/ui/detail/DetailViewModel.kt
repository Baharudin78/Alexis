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

    fun getProductSize(id : Int) = productUseCase.getProductSize(id).asLiveData()

    fun postWishlist(productItemCode: String) =
        wishlistUseCase.postWishlist(productItemCode).asLiveData()

    fun postShoppingBag(productItemCode : String, productSizeId : String, quantity : String) =
        shoppingBagUseCase.postShoppingBag(productItemCode, productSizeId, quantity)
            .asLiveData()
}