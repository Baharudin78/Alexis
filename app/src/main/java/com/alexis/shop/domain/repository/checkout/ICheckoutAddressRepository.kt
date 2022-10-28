package com.alexis.shop.domain.repository.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ICheckoutAddressRepository {
    fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<Resource<CheckoutAddressModelView>>

    fun getCheckoutAddress(): Flow<Resource<List<CheckoutAddressModelView>>>


}