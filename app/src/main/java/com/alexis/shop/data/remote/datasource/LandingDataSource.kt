package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.landing.LandingResponse
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingDataSource @Inject constructor(
    private val apiResponse : ApiService
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getLandingImage(): Flow<ApiResponse<LandingResponse>>{
        return channelFlow {
            try {
                val response = apiResponse.getLandingImage()
                if (response.data?.landingItem != null){
                    send(ApiResponse.Success(response))
                }else{
                    send(ApiResponse.Empty)
                }
            }catch (e : Exception){
                send(ApiResponse.Error(e.localizedMessage))
                Log.e("LandignDataSource", e.toString())
            }
        }
    }
}