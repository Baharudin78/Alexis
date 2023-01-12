package com.alexis.shop.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.address.PostAddressInteractor
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAddressFragmentViewModel @Inject constructor(
    private val checkoutAddressUseCase: CheckoutAddressUseCase,
    private val authUseCase: AuthUseCase,
    private val postAddressInteractor: PostAddressInteractor
) : ViewModel() {

    fun getCheckoutAddress() =
        checkoutAddressUseCase.getCheckoutAddress().asLiveData()

    fun getCourierMatrix(id : Int) =
        checkoutAddressUseCase.getCourierMetrix(id).asLiveData()

    fun deleteAddress(id : Int) =
        checkoutAddressUseCase.deleteAddress(id).asLiveData()

    fun setSetActive(id : Int) =
        checkoutAddressUseCase.setActiveAddress(id).asLiveData()

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
    ) = checkoutAddressUseCase.postCheckoutAddress(
        recipientName,
        address,
        addressTwo,
        villageId,
        postalCode,
        recipientPhoneNumber,
        asDropship,
        isDefault,
        latitude,
        longitude
    ).asLiveData()

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
    ) = checkoutAddressUseCase.updateAddress(
        id, recipientName, address, addressTwo, villageId, postalCode, recipientPhoneNumber, asDropship, isDefault, latitude, longitude
    ).asLiveData()


}