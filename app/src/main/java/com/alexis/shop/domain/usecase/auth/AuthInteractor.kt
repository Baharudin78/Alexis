package com.alexis.shop.domain.usecase.auth

import com.alexis.shop.domain.model.auth.ActivateUserModel
import com.alexis.shop.domain.model.auth.LoginModel
import com.alexis.shop.domain.model.auth.RegisterModel
import com.alexis.shop.domain.repository.auth.IAuthRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.auth.LogoutResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val repository: IAuthRepository) : AuthUseCase {
    override fun login(email: String, password: String): Flow<Resource<LoginModel>> = repository.login(email, password)

    override fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        confirmPassword : String,
        tanggalLahir : String
    ): Flow<Resource<RegisterModel>> = repository.register(name, phone, email, password,confirmPassword, tanggalLahir)

    override fun logOut(): Flow<Resource<LogoutResponse>> {
        return repository.logOut()
    }

    override fun logout() = repository.logout()

    override fun saveLoginCredential(loginModel: LoginModel) = repository.saveLoginCredential(loginModel)

    override fun getLoginCredential(): LoginModel = repository.getLoginCredential()

    override fun isUserLogin(): Boolean = repository.isUserLogin()

    override fun getUserId(): Int = repository.getUserId()
}