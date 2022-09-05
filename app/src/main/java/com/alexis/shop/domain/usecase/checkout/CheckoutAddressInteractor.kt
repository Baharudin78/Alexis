package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutAddressInteractor @Inject constructor(private val repository: ICheckoutAddressRepository): CheckoutAddressUseCase {
    override fun postCheckoutAddress(
        token: String,
        checkoutAddressModelView: CheckoutAddressModelView,
        userId: Int
    ): Flow<Resource<CheckoutAddressModelView>> {
        return repository.postCheckoutAddress(token,checkoutAddressModelView, userId)
    }

    override fun getCheckoutAddress(token : String): Flow<Resource<List<CheckoutAddressModelView>>> {
        return repository.getCheckoutAddress(token)
    }
}