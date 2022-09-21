package com.alexis.shop.domain.usecase.storelocation

import com.alexis.shop.domain.model.store_location.AllStoreLocationModel
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import kotlinx.coroutines.flow.Flow

interface StoreLocationUseCase {
    fun getAllStoreLocation(): Flow<Resource<AllStoreLocationModel>>
    fun getStoreHome() : Flow<Resource<List<AllStoreItemModel>>>
    fun getStoreLocationByName(name: String): Flow<Resource<List<StoreLocationByNameModel>>>
}