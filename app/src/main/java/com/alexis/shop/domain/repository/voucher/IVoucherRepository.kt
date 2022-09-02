package com.alexis.shop.domain.repository.voucher

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import kotlinx.coroutines.flow.Flow

interface IVoucherRepository {
    fun getAllVoucher(token : String): Flow<Resource<AllVoucherModel>>
}