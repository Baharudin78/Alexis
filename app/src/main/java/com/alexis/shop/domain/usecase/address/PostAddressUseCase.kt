package com.alexis.shop.domain.usecase.address

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressRemoteDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@ViewModelScoped
class PostAddressUseCase @Inject constructor(
    private val checkoutAddressRemoteDataSource: CheckoutAddressRemoteDataSource
) {
    suspend fun postAddress( param : HashMap<String, @JvmSuppressWildcards RequestBody>)
            : Flow<Resource<CheckoutAddressPostResponse>> {
        return flow {
            checkoutAddressRemoteDataSource.postAddress(param)
        }
    }
}