package com.alexis.shop.data.remote.voucher

import android.util.Log
import com.alexis.shop.data.remote.model.voucher.VoucherResponse
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoucherRemoteDataSource @Inject constructor(
    private val apiService : ApiService
) {
    suspend fun getAllVoucher() : Flow<ApiResponse<VoucherResponse>> {
        return flow {
            try {
                val response = apiService.getVoucher()
                if (!response.data?.voucherList.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}