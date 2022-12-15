package com.alexis.shop.data.remote.datasource

import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.sosmed.SosialMediaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocialMediaDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSocialMedia() : Flow<ApiResponse<SosialMediaResponse>> {
        return flow {
            try {
                val response = apiService.getSosialMedia()
                if (response.data.item != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.localizedMessage.orEmpty()))
            }
        }.flowOn(Dispatchers.IO)
    }
}