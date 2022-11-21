package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.profil.ProfilResponse
import com.alexis.shop.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilDataSource @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getProfile() : Flow<ApiResponse<ProfilResponse>> {
        return flow {
            try {
                val response = apiService.getProfile()
                if (response.data?.item != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                log(e.localizedMessage.orEmpty())
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
            }
        }.flowOn(Dispatchers.IO)
    }
}