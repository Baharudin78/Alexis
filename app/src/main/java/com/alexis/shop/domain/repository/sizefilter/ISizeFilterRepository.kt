package com.alexis.shop.domain.repository.sizefilter

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import kotlinx.coroutines.flow.Flow

interface ISizeFilterRepository {
    fun getSizeFilter(): Flow<Resource<List<SizeFilterModel>>>
}