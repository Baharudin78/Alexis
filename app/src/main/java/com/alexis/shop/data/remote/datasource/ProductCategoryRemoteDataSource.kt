package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.product.category.ProductCategoryNewResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoryRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllProductCategory(): Flow<ApiResponse<ProductCategoryNewResponse>> {
        return flow {
            try {
                val response = apiService.getAllProductCategory()
                if (response.data.items.isNotEmpty()) {
                    Log.e("RemoteDataS", "1")
                    emit(ApiResponse.Success(response))
                } else {
                    Log.e("RemoteDataS", "2")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataS", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}