package com.alexis.shop.ui.account.voucher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.voucher.VoucherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VoucherViewModel @Inject constructor(
    private val voucherUseCase: VoucherUseCase
) : ViewModel(){
    fun getAllVoucher() = voucherUseCase.getAllVoucher().asLiveData()
}