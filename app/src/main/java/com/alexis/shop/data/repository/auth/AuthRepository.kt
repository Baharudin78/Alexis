package com.alexis.shop.data.repository.auth

import android.util.Log
import com.alexis.shop.data.local.AuthLocalDataSource
import com.alexis.shop.data.remote.datasource.AuthRemoteDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.domain.model.auth.ActivateUserModel
import com.alexis.shop.domain.model.auth.LoginModel
import com.alexis.shop.domain.model.auth.RegisterModel
import com.alexis.shop.domain.repository.auth.IAuthRepository
import com.alexis.shop.utils.AppExecutors
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ResponseConstant.RESPONSE_EMPTY
import com.alexis.shop.data.remote.response.auth.LogoutResponse
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource,
    private val appExecutors: AppExecutors
) : IAuthRepository {

    override fun login(email: String, password: String): Flow<Resource<LoginModel>> {
        return flow<Resource<LoginModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.login(email, password).first()) {
                is ApiResponse.Success -> {
                    Log.w("LOGIN", "Sukses")
                    with(apiResponse.data) {
                        emit(Resource.Success(
                            LoginModel(
                                id = this.data.user.id.orZero(),
                                userId = this.data.user.userId.orZero(),
                                fullName = this.data.user.fullName.orEmpty(),
                                email = this.data.user.email.orEmpty(),
                                phone = this.data.user.phone.orEmpty(),
                                birthDate = this.data.user.birthDate.orEmpty(),
                                token = this.data.user.token.orEmpty()
                            )
                        ))
                    }
                }
                is ApiResponse.Empty -> emit(Resource.Error(RESPONSE_EMPTY))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }


    override fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        confirmPassword : String,
        tanggalLahir : String
    ): Flow<Resource<RegisterModel>> {
        return flow<Resource<RegisterModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.register(name, phone, email, password,confirmPassword, tanggalLahir).first()) {
                is ApiResponse.Success -> {
                    with(apiResponse.data) {
                        emit(Resource.Success(
                            RegisterModel(
                                fullName = this.data.user.fullName.orEmpty(),
                                email = this.data.user.email.orEmpty(),
                                phone = this.data.user.phone.orEmpty(),
                                password = this.data.user.password.orEmpty(),
                                confirmPassword = this.data.user.confirmPassword.orEmpty(),
                                tanggal = this.data.user.tanggal.orEmpty()
                            )
                        ))
                    }
                }
                is ApiResponse.Empty -> emit(Resource.Error(RESPONSE_EMPTY))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun logOut(): Flow<Resource<LogoutResponse>> {
        return flow<Resource<LogoutResponse>> {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.logout().first()){
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error(RESPONSE_EMPTY))
            }
        }
    }


    override fun logout() {
        localDataSource.logout()
    }

    override fun saveLoginCredential(loginModel: LoginModel) {
        appExecutors.diskIO().execute {
            localDataSource.saveLoginCredential(loginModel)
        }
    }

    override fun getLoginCredential(): LoginModel {
        return localDataSource.getLoginCredential()
    }

    override fun isUserLogin(): Boolean {
        return localDataSource.isUserLogin()
    }

    override fun getUserId(): Int {
        return localDataSource.getUserId()
    }
}