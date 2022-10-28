package com.alexis.shop.ui.checkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.usecase.address.PostAddressInteractor
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressInteractor
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class SelectAddressFragmentViewModel @Inject constructor(
    private val checkoutAddressUseCase: CheckoutAddressUseCase,
    private val authUseCase: AuthUseCase,
    private val postAddressInteractor: PostAddressInteractor
) : ViewModel() {

    fun getCheckoutAddress() =
        checkoutAddressUseCase.getCheckoutAddress().asLiveData()

    fun postCheckoutAddress(data: CheckoutAddressModelView) =
        checkoutAddressUseCase.postCheckoutAddress(data).asLiveData()


}