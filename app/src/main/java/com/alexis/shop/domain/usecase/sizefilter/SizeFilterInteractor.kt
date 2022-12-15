package com.alexis.shop.domain.usecase.sizefilter

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.domain.repository.sizefilter.ISizeFilterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SizeFilterInteractor @Inject constructor(
    private val repository: ISizeFilterRepository
    ) : SizeFilterUseCase {
    override fun getSizeFilter(): Flow<Resource<List<SizeFilterModel>>> {
        return repository.getSizeFilter()
    }

    override fun postSizeFilter(sizeId:  IntArray): Flow<Resource<List<ProductBaruModel>>> {
        return repository.postProductFilter(sizeId)
    }
}