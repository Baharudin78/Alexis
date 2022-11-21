package com.alexis.shop.domain.usecase.sizefilter

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import kotlinx.coroutines.flow.Flow

interface SizeFilterUseCase {
    fun getSizeFilter(): Flow<Resource<List<SizeFilterModel>>>
    fun postSizeFilter(sizeId : Array<Int>) : Flow<Resource<List<ProductBaruModel>>>
}