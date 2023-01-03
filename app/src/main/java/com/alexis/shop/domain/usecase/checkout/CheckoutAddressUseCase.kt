package com.alexis.shop.domain.usecase.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.address.AddressListModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.flow.Flow

interface CheckoutAddressUseCase {
    fun postCheckoutAddress(
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
    ): Flow<Resource<CheckoutAddressModelView>>

    fun updateAddress(
        id : Int,
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
    ) : Flow<Resource<MessageResponse>>

    fun getCheckoutAddress(): Flow<Resource<AddressListModel>>
    fun deleteAddress(id : Int) : Flow<Resource<MessageResponse>>
    fun setActiveAddress(id : Int) : Flow<Resource<MessageResponse>>
}