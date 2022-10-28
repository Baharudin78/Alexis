package com.alexis.shop.data.remote.response.checkout

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutAddressRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postCheckoutAddress(
        checkoutAddressModelView: CheckoutAddressModelView,
    ): Flow<ApiResponse<CheckoutAddressPostResponse>> {
        return flow {
            try {
                val response = apiService.postCheckOutAddress(
                    checkoutAddressModelView.recipientName,
                    checkoutAddressModelView.address,
                    checkoutAddressModelView.addressTwo,
                    checkoutAddressModelView.villageId,
                    checkoutAddressModelView.postalCode,
                    checkoutAddressModelView.recipientPhoneNumber,
                    checkoutAddressModelView.isDefault,
                    checkoutAddressModelView.asDropship,
                    checkoutAddressModelView.latitude,
                    checkoutAddressModelView.longitude
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


    suspend fun getCheckoutAddress(): Flow<ApiResponse<CheckoutAddressGetResponse>> {
        return flow {
            try {
                val response = apiService.getCheckOutAddress()
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