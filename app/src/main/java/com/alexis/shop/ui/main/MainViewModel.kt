package com.alexis.shop.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.landing.LandingUseCase
import com.alexis.shop.domain.usecase.product.ProductUseCase
import com.alexis.shop.domain.usecase.product.category.ProductCategoryUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val wishlistUseCase: WishlistUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    private val authUseCase: AuthUseCase,
    private val landingImge : LandingUseCase,
    private val productCategoryUseCase: ProductCategoryUseCase
) : ViewModel() {
    private var productCategory: MutableLiveData<List<ProductCategoryNewModel>> = MutableLiveData()

    fun getAllProduct() = productUseCase.getAllProduct().asLiveData()

    fun getProductCategory() = productCategoryUseCase.getAllProductCategory().asLiveData()


    fun getLandingImage() = landingImge.getLandingImage().asLiveData()

    fun getCategoryById(id : String) = productCategoryUseCase.getCatergoryById(id)

    fun getWishlist() = wishlistUseCase.getWishlist().asLiveData()

    fun getShoppingBag() = shoppingBagUseCase.getShoppingBag().asLiveData()

    fun isUserAuthenticated() = authUseCase.isUserLogin()
}