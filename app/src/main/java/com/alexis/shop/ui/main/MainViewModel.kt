package com.alexis.shop.ui.main

import androidx.lifecycle.*
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.category.subcategory.SubCategoryModel
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.product.ProductUseCase
import com.alexis.shop.domain.usecase.product.category.ProductCategoryUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val wishlistUseCase: WishlistUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    private val authUseCase: AuthUseCase,
    private val productCategoryUseCase: ProductCategoryUseCase
) : ViewModel() {
    private var productCategory: MutableLiveData<List<ProductCategoryModel>> = MutableLiveData()
    private var subProductCategory : MutableLiveData<List<SubCategoryModel>> = MutableLiveData()

    fun getAllProduct() = productUseCase.getAllProduct().asLiveData()

    fun callProductCategoryData() {
        viewModelScope.launch {
            when (val response = productCategoryUseCase.getAllProductCategory()) {
                is Resource.Loading -> {}
                is Resource.Success -> productCategory.postValue(response.data)
                is Resource.Error -> productCategory.postValue(listOf())
            }
        }
    }

    fun getSubCategoryData() {
        viewModelScope.launch {
            when(val response = productCategoryUseCase.getSubProductCategory()) {
                is Resource.Loading -> {}
                is Resource.Success -> subProductCategory.postValue(response.data)
                is Resource.Error -> subProductCategory.postValue(listOf())
            }
        }
    }

    fun getProductCategoryData(): MutableLiveData<List<ProductCategoryModel>> {
        return productCategory
    }

    fun getWishlist() = wishlistUseCase.getWishlist(authUseCase.getUserId()).asLiveData()

    fun getShoppingBag() = shoppingBagUseCase.getShoppingBag(authUseCase.getUserId()).asLiveData()

    fun isUserAuthenticated() = authUseCase.isUserLogin()
}