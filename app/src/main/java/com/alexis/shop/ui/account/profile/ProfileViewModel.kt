package com.alexis.shop.ui.account.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.profil.ProfilUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfilUseCase) : ViewModel() {
    fun getProfileData() = useCase.getProfil().asLiveData()
    fun updateName(name : String) = useCase.updateName(name).asLiveData()
    fun updatePhone(phone : String) = useCase.updatePhone(phone).asLiveData()
}