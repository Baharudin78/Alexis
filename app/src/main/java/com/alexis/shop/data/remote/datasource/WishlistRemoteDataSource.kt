package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.wishlist.WishlistGetResponse
import com.alexis.shop.data.remote.response.wishlist.WishlistPostResponse
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun postWishlist(productItemCode: String): Flow<ApiResponse<WishlistPostResponse>> {
        return flow {
            try {
                val response = apiService.postWishlist(productItemCode)
                if (!response.data?.postWishList?.customeId.isNullOrEmpty()) {
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

    suspend fun getWishlist(): Flow<ApiResponse<WishlistGetResponse>> {
        return flow {
            try {
                val response = apiService.getWishlist()
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

    suspend fun getWishlistBySize(sizeId : Int) : Flow<ApiResponse<WishlistGetResponse>> {
        return flow {
            try {
                val response = apiService.getWishlistBySize(sizeId)
                if (!response.data?.wishlist.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteWishlist(id : Int) : Flow<ApiResponse<MessageResponse>> {
        return flow {
            try {
                val response = apiService.deleteWishlist(id)
                emit(ApiResponse.Success(response))
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
            }
        }.flowOn(Dispatchers.IO)
    }
}