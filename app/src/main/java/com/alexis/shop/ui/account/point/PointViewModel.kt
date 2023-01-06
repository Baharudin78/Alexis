package com.alexis.shop.ui.account.point

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.order.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase
) : ViewModel(){

    fun getPointHistory() = orderUseCase.getPointHistory().asLiveData()
}