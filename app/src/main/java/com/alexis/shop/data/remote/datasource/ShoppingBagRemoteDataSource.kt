package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.shoppingbag.ShoppingBagPostData
import com.alexis.shop.data.remote.response.shoppingbag.ShopingBagNewResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingBagRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postShoppingBag(productItemCode: String, sizeId: String,quantity: String): Flow<ApiResponse<ShoppingBagPostData>> {
        return flow {
            try {
                val response = apiService.postShoppingBag(productItemCode,sizeId, quantity)
                if (response.data != null) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getShoppingBag(): Flow<ApiResponse<ShopingBagNewResponse>> {
        return flow {
            try {
                val response = apiService.getShoppingBag()
                if (response.data.items != null) {
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteShoppingBag(cartId: Int): Flow<ApiResponse<String>> {
        return flow {
            try {
                val response = apiService.deleteShoppingBag(cartId)
                if (!response.data?.message.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response.data?.message.orEmpty()))
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