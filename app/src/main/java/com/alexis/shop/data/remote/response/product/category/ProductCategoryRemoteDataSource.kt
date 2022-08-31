package com.alexis.shop.data.remote.response.product.category

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.product.category.subcategory.SubProductCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoryRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllProductCategory(): Flow<ApiResponse<ProductCategoryResponse>> {
        return flow {
            try {
                val response = apiService.getAllProductCategory()
                if (response.data != null && response.data.productCategory?.isNotEmpty() == true) {
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

    suspend fun getSubProductCategory(name : String) : Flow<ApiResponse<SubProductCategoryResponse>> {
        return flow {
            try {
                val response = apiService.getSubProductCategory(name)
                if (response.data != null && response.data.subProductCategory?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
               // emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", " Sub category + $e")
            }
        }
    }
}