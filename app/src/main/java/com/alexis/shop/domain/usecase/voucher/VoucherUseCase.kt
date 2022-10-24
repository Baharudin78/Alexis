package com.alexis.shop.domain.usecase.voucher

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.store_location.AllStoreLocationModel
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import kotlinx.coroutines.flow.Flow

interface VoucherUseCase {
    fun getAllVoucher(): Flow<Resource<AllVoucherModel>>
}