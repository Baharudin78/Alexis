package com.alexis.shop.data.remote.wishlist

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postWishlist(productDetailCode: String, userId: Int): Flow<ApiResponse<WishlistPostResponse>> {
        return flow {
            try {
                val response = apiService.postWishlist(productDetailCode, userId)
                if (!response.data?.productId.isNullOrEmpty()) {
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

    suspend fun getWishlist(userId: Int): Flow<ApiResponse<WishlistGetResponse>> {
        return flow {
            try {
                val response = apiService.getWishlist(userId)
                if (!response.data?.wishlist.isNullOrEmpty()) {
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