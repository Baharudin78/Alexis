package com.alexis.shop.data.remote.product

import android.util.Log
import com.alexis.shop.data.remote.model.productbaru.ProductBaruResponse
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRemoteDataSource @Inject constructor(private val apiService: ApiService) {
//    suspend fun getAllProduct(): Flow<ApiResponse<ProductsResponse>> {
//        return flow {
//            try {
//                val response = apiService.getAllProduct()
//                if (response.data?.products?.isNotEmpty() == true) {
//                    emit(ApiResponse.Success(response))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    suspend fun getAllProduct(): Flow<ApiResponse<ProductBaruResponse>> {
        return flow {
            try {
                Log.d("RemoteDataSource", "1")
                val response = apiService.getAllProduct()

                if (response.data?.product?.isNotEmpty()== true) {
                    Log.d("RemoteDataSource", "2")
                    emit(ApiResponse.Success(response))
                }
                else {
                    Log.d("RemoteDataSource", "3")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSource", "4")
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProductById(productId: Int): Flow<ApiResponse<ProductsGetByIdResponse>> {
        return flow {
            try {
                val response = apiService.getProductById(productId)
                if (response.data?.product != null) {
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