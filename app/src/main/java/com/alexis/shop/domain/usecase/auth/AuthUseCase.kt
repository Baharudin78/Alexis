package com.alexis.shop.domain.usecase.auth

import com.alexis.shop.domain.model.auth.ActivateUserModel
import com.alexis.shop.domain.model.auth.LoginModel
import com.alexis.shop.domain.model.auth.RegisterModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun login(email: String, password: String): Flow<Resource<LoginModel>>
    fun register(name: String, phone: String, email: String, password: String): Flow<Resource<RegisterModel>>
    fun activeUser(email: String): Flow<Resource<ActivateUserModel>>
    fun logout()
    fun saveLoginCredential(loginModel: LoginModel)
    fun getLoginCredential(): LoginModel
    fun isUserLogin():Boolean
    fun getUserId(): Int
}