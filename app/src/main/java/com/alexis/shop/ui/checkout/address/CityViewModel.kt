package com.alexis.shop.ui.checkout.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.domain.usecase.city.CityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityUseCase: CityUseCase
) : ViewModel(){

    private val koteState = MutableStateFlow<List<CityItemModel>>(mutableListOf())
    val mKotaState : StateFlow<List<CityItemModel>> get() = koteState

    fun getCitySearch(name : String) = cityUseCase.getCity(name).asLiveData()

}