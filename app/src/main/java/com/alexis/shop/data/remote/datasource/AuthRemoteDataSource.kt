package com.alexis.shop.data.remote.datasource

import android.util.Log
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.network.ResponseConstant
import com.alexis.shop.data.remote.response.auth.LoginResponse
import com.alexis.shop.data.remote.response.auth.LogoutResponse
import com.alexis.shop.data.remote.response.auth.RegisterResponse
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
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
                Log.d("REGISTER", "1")
                val response = apiService.registration(name, phone, email, password,confirmPassword, tanggalLahir)
                if (!response.data.user.email.isNullOrEmpty()) {
                    Log.d("REGISTER", "2")

                    emit(ApiResponse.Success(response))
                } else {
                    Log.d("REGISTER", "3")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.d("REGISTER", "4")
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun login(email: String, password: String): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.login(email, password)
                if (response.data.user.id.orZero() > 0) {
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

    suspend fun logout(): Flow<ApiResponse<LogoutResponse>>{
        return flow {
            try {
                val response = apiService.logOut()
                emit(ApiResponse.Success(response))
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.message.toString()))
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