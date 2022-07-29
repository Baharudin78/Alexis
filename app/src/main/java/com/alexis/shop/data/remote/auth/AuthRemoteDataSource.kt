package com.alexis.shop.data.remote.auth

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.network.ResponseConstant
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        confirmPassword : String,
        tanggalLahir : String
    ): Flow<ApiResponse<RegisterResponse>> {
        return flow {
            try {
                val response = apiService.registration(name, phone, email, password,confirmPassword, tanggalLahir)
                if (!response.data.email.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun login(email: String, password: String): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                Log.w("LOGIN", "1")
                val response = apiService.login(email, password)
                Log.w("LOGIN", "${response}")
                if (response.data.user.id.orZero() > 0) {
                    Log.w("LOGIN", "2")
                    Log.w("LOGIN", "${response.data}")
                    emit(ApiResponse.Success(response))
                } else {
                    Log.w("LOGIN", "3")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.w("LOGIN", "4")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun activate(email: String): Flow<ApiResponse<String>> {
        return flow {
            try {
                apiService.activateUser(email)
                emit(ApiResponse.Success(ResponseConstant.RESPONSE_200))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}