package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.response.contact.ContactResponse
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactDataSource  @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getContact() : Flow<ApiResponse<ContactResponse>> {
         return flow {
             try {
                 val response = apiService.getContact()
                 if (response.data.items.isNotEmpty()) {
                     emit(ApiResponse.Success(response))
                 }else{
                     Log.d("REMOTES", "2")
                     emit(ApiResponse.Empty)
                 }
             }catch (e : Exception) {
                 Log.d("RemoteDataSource", e.localizedMessage.orEmpty())
                 emit(ApiResponse.Error(e.toString()))
             }
         }.flowOn(Dispatchers.IO)
    }
}