package com.alexis.shop.data.repository.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressItem
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressRemoteDataSource
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CheckoutAddressRepository @Inject constructor(
    private val remoteDataSource: CheckoutAddressRemoteDataSource
) : ICheckoutAddressRepository {

    override fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<Resource<CheckoutAddressModelView>> {
        return flow<Resource<CheckoutAddressModelView>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postCheckoutAddress(
                checkoutAddressModelView,
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
                                asDropship = item?.asDropship.orEmpty(),
                                isDefault = item?.isDefault.orEmpty(),
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

    override fun getCheckoutAddress(): Flow<Resource<List<CheckoutAddressModelView>>> {
        return flow<Resource<List<CheckoutAddressModelView>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getCheckoutAddress().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateCheckoutAddressModelView(
                            apiResponse.data.data?.address
                        )
                    )
                )
                is ApiResponse.Empty -> listOf<CheckoutAddressModelView>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }


    private fun generateCheckoutAddressModelView(response: List<CheckoutAddressItem>?): List<CheckoutAddressModelView> {
        return response?.map {item ->
            CheckoutAddressModelView(
                recipientName = item.recipientName.orEmpty(),
                address = item.address.orEmpty(),
                addressTwo = item.addressTwo.orEmpty(),
                villageId = item.villageId.orEmpty(),
                postalCode = item.postalCode.orEmpty(),
                recipientPhoneNumber = item.recipientPhoneNumber.orEmpty(),
                asDropship = item.asDropship.orEmpty(),
                isDefault = item.isDefault.orEmpty(),
                latitude = item.latitude.orEmpty(),
                longitude = item.longitude.orEmpty()
            )
        }.orEmpty()
    }
}