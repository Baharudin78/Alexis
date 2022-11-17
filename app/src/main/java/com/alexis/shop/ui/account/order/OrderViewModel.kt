package com.alexis.shop.ui.account.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.order.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private var orderUseCase: OrderUseCase
) : ViewModel(){

    fun getOrder() = orderUseCase.getOrder().asLiveData()
}