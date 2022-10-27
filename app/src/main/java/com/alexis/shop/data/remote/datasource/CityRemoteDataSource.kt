package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.response.kota.CityResponse
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRemoteDataSource @Inject constructor(
    private val apiService : ApiService
) {
    suspend fun getCity(token : String, city : String) : Flow<ApiResponse<CityResponse>> {
        return flow {
            try {
                val response = apiService.getKelurahan(token, city)
                if (!response.data?.cityItem.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}