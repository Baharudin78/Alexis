package com.alexis.shop.data.repository.checkout

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.checkout.CheckoutAddressItem
import com.alexis.shop.data.remote.checkout.CheckoutAddressRemoteDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import com.alexis.shop.utils.orFalse
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckoutAddressRepository @Inject constructor(
    private val remoteDataSource: CheckoutAddressRemoteDataSource
) : ICheckoutAddressRepository {

    override fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
        userId: Int
    ): Flow<Resource<CheckoutAddressModelView>> {
        return flow<Resource<CheckoutAddressModelView>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postCheckoutAddress(
                checkoutAddressModelView,
                userId
            ).first()) {
                is ApiResponse.Success -> {
                    val item = apiResponse.data.data?.address
                    emit(
                        Resource.Success(
                            CheckoutAddressModelView(
                                address = item?.address.orEmpty(),
                                asDropship = item?.asDropship.orFalse(),
                                isDefault = item?.isDefault == true,
                                typeAddress = item?.typeAddress.orEmpty(),
                                recipientPhoneNumber = item?.recipientPhoneNumber.orEmpty(),
                                recipientName = item?.recipientName.orEmpty(),
                                postalCode = item?.postalCode.orZero(),
                                otherDetail = item?.otherDetail.orEmpty()
                            )
                        )
                    )
                }
                is ApiResponse.Empty -> listOf<CheckoutAddressModelView>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getCheckoutAddress(userId: Int): Flow<Resource<List<CheckoutAddressModelView>>> {
        return flow<Resource<List<CheckoutAddressModelView>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getCheckoutAddress(userId).first()) {
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
        return response?.map {
            CheckoutAddressModelView(
                address = it.address.orEmpty(),
                asDropship = it.asDropship != 0,
                isDefault = it.isDefault != 0,
                typeAddress = it.typeAddress.orEmpty(),
                recipientPhoneNumber = it.recipientPhoneNumber.orEmpty(),
                checkoutAddressId = it.id.orZero(),
                recipientName = it.recipientName.orEmpty(),
                postalCode = it.postalCode.orZero(),
                otherDetail = it.otherDetail.orEmpty()
            )
        }.orEmpty()
    }
}