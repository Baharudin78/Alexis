package com.alexis.shop.data.remote.storelocation

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
class StoreLocationRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllStoreLocation(): Flow<ApiResponse<AllStoreLocationResponse>> {
        return flow {
            try {
                val response = apiService.getAllStoreLocation()
                if (!response.data?.storeLocation.isNullOrEmpty()) {
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

    suspend fun getStoreLocationByName(name: String): Flow<ApiResponse<StoreLocationByNameResponse>> {
        return flow {
            try {
                val response = apiService.getStoreLocationByName(name)
                if (response.data != null) {
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