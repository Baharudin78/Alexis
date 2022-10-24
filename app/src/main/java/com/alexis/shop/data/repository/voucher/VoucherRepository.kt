package com.alexis.shop.data.repository.voucher

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.model.voucher.VoucherItem
import com.alexis.shop.data.remote.model.voucher.VoucherResponse
import com.alexis.shop.data.remote.model.voucher.VoucherType
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.storelocation.StoreLocationRemoteDataSource
import com.alexis.shop.data.remote.voucher.VoucherRemoteDataSource
import com.alexis.shop.domain.model.voucher.AllVoucherModel
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.domain.repository.voucher.IVoucherRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoucherRepository @Inject constructor(
    private val remoteDataSource: VoucherRemoteDataSource
): IVoucherRepository{
    override fun getAllVoucher(): Flow<Resource<AllVoucherModel>> {
        return flow <Resource<AllVoucherModel>>{
            when(val apiResponse = remoteDataSource.getAllVoucher().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        convertAllVoucherResponseToModel(
                            apiResponse.data.data?.voucherList
                        )
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun convertAllVoucherResponseToModel(data : List<VoucherItem?>?) : AllVoucherModel {
        return if (!data.isNullOrEmpty()) {
            AllVoucherModel(
                data = data.map {
                    VoucherItemModel(
                        id = it?.id.orZero(),
                        name = it?.name.orEmpty(),
                        description = it?.description.orEmpty(),
                        discount = it?.discount.orZero(),
                        expiredDate = it?.expiredDate.orEmpty(),
                        voucherTypeId = it?.voucherTypeId.orZero(),
                    )
                }
            )
        } else AllVoucherModel()
    }

}