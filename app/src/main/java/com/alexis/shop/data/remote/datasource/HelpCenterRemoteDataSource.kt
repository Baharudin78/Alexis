package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterItem
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HelpCenterRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getHelpCenter() : Flow<ApiResponse<HelpCenterResponse>> {
        return flow {
            try {
                val response = apiService.getHelpCenter()
                if (response.data.items.isNotEmpty()) {
                    Log.d("RemoteDataSource", "1")
                    emit(ApiResponse.Success(response))
                }else{
                    Log.d("RemoteDataSource", "2")
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                Log.d("RemoteDataSource", e.localizedMessage.orEmpty())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}