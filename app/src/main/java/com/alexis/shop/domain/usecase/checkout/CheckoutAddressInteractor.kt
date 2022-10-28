package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutAddressInteractor @Inject constructor(private val repository: ICheckoutAddressRepository): CheckoutAddressUseCase {
    override fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<Resource<CheckoutAddressModelView>> {
        return repository.postCheckoutAddress(checkoutAddressModelView)
    }

    override fun getCheckoutAddress(): Flow<Resource<List<CheckoutAddressModelView>>> {
        return repository.getCheckoutAddress()
    }
}