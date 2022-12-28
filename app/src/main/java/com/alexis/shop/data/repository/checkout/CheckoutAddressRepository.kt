package com.alexis.shop.data.repository.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressItem
import com.alexis.shop.data.remote.datasource.CheckoutAddressRemoteDataSource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.domain.model.address.AddressListModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import com.alexis.shop.utils.orZero
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckoutAddressRepository @Inject constructor(
    private val remoteDataSource: CheckoutAddressRemoteDataSource
) : ICheckoutAddressRepository {

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
        return flow<Resource<CheckoutAddressModelView>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postCheckoutAddress(
                recipientName, address, addressTwo, villageId, postalCode, recipientPhoneNumber, asDropship, isDefault, latitude, longitude
            ).first()) {
                is ApiResponse.Success -> {
                    val item = apiResponse.data.data?.address
                    emit(
                        Resource.Success(
                            CheckoutAddressModelView(
                                recipientName = item?.recipientName.orEmpty(),
                                address = item?.address.orEmpty(),
                                addressTwo = item?.addressTwo.orEmpty(),
                                villageId = item?.villageId.orEmpty(),
                                postalCode = item?.postalCode.orEmpty(),
                                recipientPhoneNumber = item?.recipientPhoneNumber.orEmpty(),
                                asDropship = item?.asDropship?.toInt().orZero(),
                                isDefault = item?.isDefault?.toInt().orZero(),
                                latitude = item?.latitude.orEmpty(),
                                longitude = item?.longitude.orEmpty()
                            )
                        )
                    )
                }
                is ApiResponse.Empty -> listOf<CheckoutAddressModelView>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getCheckoutAddress(): Flow<Resource<AddressListModel>> {
        return flow<Resource<AddressListModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getCheckoutAddress().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateCheckoutAddressModelView(apiResponse.data.data?.address)
                    )
                )
                is ApiResponse.Empty -> listOf<AddressItemModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun updateAddress(
        id: Int,
        recipientName: String,
        address: String,
        addressTwo: String,
        villageId: String,
        postalCode: String,
        recipientPhoneNumber: String,
        asDropship: Int,
        isDefault: Int,
        latitude: String,
        longitude: String
    ): Flow<Resource<MessageResponse>> {
        return flow<Resource<MessageResponse>> {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.updateAddress(id, recipientName, address, addressTwo, villageId, postalCode, recipientPhoneNumber, asDropship, isDefault, latitude, longitude).first()){
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
            }
        }
    }

    override fun deleteAddress(id: Int): Flow<Resource<MessageResponse>> {
        return flow<Resource<MessageResponse>> {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.deleteAddress(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Empty -> emit(Resource.Loading())
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }


    private fun generateCheckoutAddressModelView(response: List<CheckoutAddressItem?>?): AddressListModel {
        return if (!response.isNullOrEmpty()) {
            AddressListModel(
                address = response.map {
                    AddressItemModel(
                        id = it?.id.orZero(),
                        customerId = it?.customerId.orZero(),
                        recipientName = it?.recipientName.orEmpty(),
                        address = it?.address.orEmpty(),
                        addressTwo = it?.addressTwo.orEmpty(),
                        villageId = it?.villageId.orEmpty(),
                        latitude = it?.latitude.orEmpty(),
                        longitude = it?.longitude.orEmpty(),
                        postalCode = it?.postalCode.orEmpty(),
                        recipientPhoneNumber = it?.recipientPhoneNumber.orEmpty(),
                        isDefault = it?.isDefault.orZero(),
                        asDropship = it?.asDropship.orZero()
                    )
                }
            )
        }else{
            AddressListModel()
        }
    }
}