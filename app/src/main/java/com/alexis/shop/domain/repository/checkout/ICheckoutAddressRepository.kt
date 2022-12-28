package com.alexis.shop.domain.repository.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.address.AddressListModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ICheckoutAddressRepository {
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
       // checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<Resource<CheckoutAddressModelView>>

    fun getCheckoutAddress(): Flow<Resource<AddressListModel>>

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

    fun deleteAddress(id : Int) : Flow<Resource<MessageResponse>>

}