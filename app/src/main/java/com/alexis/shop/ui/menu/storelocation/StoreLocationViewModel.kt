package com.alexis.shop.ui.menu.storelocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.storelocation.StoreLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreLocationViewModel @Inject constructor(private val useCase: StoreLocationUseCase) : ViewModel() {
    fun getAllStoreLocation() = useCase.getAllStoreLocation().asLiveData()
    fun getStoreLocationByName(name: String) = useCase.getStoreLocationByName(name).asLiveData()
}