package com.alexis.shop.data.remote.response.checkout

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutAddressRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
        userId: Int
    ): Flow<ApiResponse<CheckoutAddressPostResponse>> {
        return flow {
            try {
                val response = apiService.postCheckOutAddress(
                    checkoutAddressModelView.typeAddress,
                    userId,
                    checkoutAddressModelView.recipientName,
                    checkoutAddressModelView.address,
                    checkoutAddressModelView.otherDetail,
                    checkoutAddressModelView.postalCode,
                    checkoutAddressModelView.recipientPhoneNumber,
                    checkoutAddressModelView.isDefault,
                    checkoutAddressModelView.asDropship
                )
                if (!response.data?.address?.recipientName.isNullOrBlank()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCheckoutAddress(userId: Int): Flow<ApiResponse<CheckoutAddressGetResponse>> {
        return flow {
            try {
                val response = apiService.getCheckOutAddress(userId)
                if (!response.data?.address.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}