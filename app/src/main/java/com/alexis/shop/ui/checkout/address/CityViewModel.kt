package com.alexis.shop.ui.checkout.address

import androidx.lifecycle.ViewModel
import com.alexis.shop.domain.usecase.city.CityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityUseCase: CityUseCase
) : ViewModel(){
    fun getCitySearch(token : String, name : String) = cityUseCase.getAllVoucher(token, name)
}