package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.address.AddressListModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutAddressInteractor @Inject constructor(private val repository: ICheckoutAddressRepository): CheckoutAddressUseCase {
    override fun postCheckoutAddress(
        recipientName: String,
        address: String,
        addressTwo: String,
        villageId: String ,
        postalCode: String ,
        recipientPhoneNumber: String ,
        asDropship: Int ,
        isDefault: Int ,
        latitude: String ,
        longitude: String ,
    ): Flow<Resource<CheckoutAddressModelView>> {
        return repository.postCheckoutAddress(recipientName, address, addressTwo, villageId, postalCode, recipientPhoneNumber, asDropship, isDefault, latitude, longitude)
    }

    override fun getCheckoutAddress(): Flow<Resource<AddressListModel>> {
        return repository.getCheckoutAddress()
    }
}