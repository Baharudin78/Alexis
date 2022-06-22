package com.alexis.shop.ui.account.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.model.auth.LoginModel
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthUseCase): ViewModel() {
    fun login(email: String, password: String) = useCase.login(email, password).asLiveData()

    fun saveLoginCredential(loginModel: LoginModel) = useCase.saveLoginCredential(loginModel)
}