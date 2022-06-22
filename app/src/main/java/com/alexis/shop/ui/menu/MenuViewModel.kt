package com.alexis.shop.ui.menu

import androidx.lifecycle.ViewModel
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
    fun isUserLogin() = useCase.isUserLogin()
}