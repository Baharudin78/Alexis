package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.productbaru.ProductBaruResponse
import com.alexis.shop.data.remote.response.sizefilter.SizesItemResponse
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

    suspend fun postSizeFilter(sizeId : Array<Int>) : Flow<ApiResponse<ProductBaruResponse>> {
        return flow<ApiResponse<ProductBaruResponse>> {
            try {
                val response = apiService.postSize(sizeId)
                if (response.data?.product?.isNotEmpty()== true) {
                    Log.d("FILTERDSDSDS"," berhasill")
                    emit(ApiResponse.Success(response))
                }else {
                    Log.d("FILTERDSDSDS", "kosong")
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                Log.d("FILTERDSDSDS", e.localizedMessage.orEmpty())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}