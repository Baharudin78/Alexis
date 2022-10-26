package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.landing.LandingResponse
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingDataSource @Inject constructor(
    private val apiResponse : ApiService
) {
    suspend fun getLandingImage(): Flow<ApiResponse<LandingResponse>>{
        return flow {
            try {
                val response = apiResponse.getLandingImage()
                if (response.data?.landingItem != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.localizedMessage))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }
}