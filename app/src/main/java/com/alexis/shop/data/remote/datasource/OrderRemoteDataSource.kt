package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.order.OrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getOrder() : Flow<ApiResponse<OrderResponse>> {
        return flow {
            try {
                val response = apiService.getAllOrder()
                if (response.data.items.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
                Log.d("REMOTEDATASOURCE", e.localizedMessage.orEmpty())
            }
        }.flowOn(Dispatchers.IO)
    }
}