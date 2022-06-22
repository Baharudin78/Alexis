package com.alexis.shop.ui.account.profile

import androidx.lifecycle.ViewModel
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
    fun getProfileData() = useCase.getLoginCredential()
}