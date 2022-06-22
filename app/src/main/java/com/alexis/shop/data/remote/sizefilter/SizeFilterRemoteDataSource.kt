package com.alexis.shop.data.remote.sizefilter

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
class SizeFilterRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getSizeFilter(): Flow<ApiResponse<List<SizesItemResponse>?>> {
        return flow {
            try {
                val response = apiService.getSizeFilter()
                if (response.data != null && response.data.sizes?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response.data.sizes))
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