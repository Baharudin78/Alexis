package com.alexis.shop.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAddressFragmentViewModel @Inject constructor(
    private val checkoutAddressUseCase: CheckoutAddressUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    fun getCheckoutAddress() =
        checkoutAddressUseCase.getCheckoutAddress(authUseCase.getUserId()).asLiveData()

    fun postCheckoutAddress(data: CheckoutAddressModelView) =
        checkoutAddressUseCase.postCheckoutAddress(data, authUseCase.getUserId()).asLiveData()
}