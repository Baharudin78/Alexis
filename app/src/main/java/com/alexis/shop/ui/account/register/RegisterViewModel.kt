package com.alexis.shop.ui.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
    fun register(name: String, phone: String, email: String, password: String) =
        useCase.register(name, phone, email, password).asLiveData()
}