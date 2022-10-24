package com.alexis.shop.domain.usecase.voucher

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.voucher.VoucherRepository
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VoucherInteractor @Inject constructor(
    private val voucherRepository: VoucherRepository
) : VoucherUseCase{
    override fun getAllVoucher(): Flow<Resource<AllVoucherModel>> {
        return voucherRepository.getAllVoucher()
    }
}