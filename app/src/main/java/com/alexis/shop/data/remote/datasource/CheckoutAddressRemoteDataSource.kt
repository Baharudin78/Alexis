package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressGetResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutAddressRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postCheckoutAddress(
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
    ): Flow<ApiResponse<CheckoutAddressPostResponse>> {
        return flow {
            try {
                val response = apiService.postCheckOutAddress(
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

    suspend fun updateAddress(
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
    ) : Flow<ApiResponse<MessageResponse>> {
        return flow {
            try {
                val response = apiService.updateAdress(
                    id,
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
                )
                if (response.code == 202) {
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
            }
        }
    }


    suspend fun getCheckoutAddress(): Flow<ApiResponse<CheckoutAddressGetResponse>> {
        return flow {
            try {
                val response = apiService.getCheckOutAddress()
                if (!response.data?.address.isNullOrEmpty()) {
                    log("location adresss success")
                    emit(ApiResponse.Success(response))
                } else {
                    log("location adresss success")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                log("location adresss error + ${e.localizedMessage}")
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}