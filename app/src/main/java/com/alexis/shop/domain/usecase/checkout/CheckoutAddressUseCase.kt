package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.flow.Flow

interface CheckoutAddressUseCase {
    fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<Resource<CheckoutAddressModelView>>

    fun getCheckoutAddress(): Flow<Resource<List<CheckoutAddressModelView>>>
}