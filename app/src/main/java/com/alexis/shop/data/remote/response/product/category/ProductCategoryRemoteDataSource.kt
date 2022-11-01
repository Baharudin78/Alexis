package com.alexis.shop.data.remote.response.product.category

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.product.categoritwo.ProductCategoryNewResponse
import com.alexis.shop.data.remote.response.product.category.subcategory.SubProductCategoryResponse
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
                if (response.data != null && response.data.items.isNotEmpty()) {
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