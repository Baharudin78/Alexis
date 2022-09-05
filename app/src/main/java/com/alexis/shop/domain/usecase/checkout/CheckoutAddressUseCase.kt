package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.flow.Flow

interface CheckoutAddressUseCase {
    fun postCheckoutAddress(
        token: String,
        checkoutAddressModelView: CheckoutAddressModelView,
        userId: Int
    ): Flow<Resource<CheckoutAddressModelView>>

    fun getCheckoutAddress(token: String): Flow<Resource<List<CheckoutAddressModelView>>>
}