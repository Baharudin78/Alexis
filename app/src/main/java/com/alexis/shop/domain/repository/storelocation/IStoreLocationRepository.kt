package com.alexis.shop.domain.repository.storelocation

import com.alexis.shop.domain.model.store_location.AllStoreLocationModel
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface IStoreLocationRepository {
    fun getAllStoreLocation(): Flow<Resource<AllStoreLocationModel>>
    fun getStoreLocationByName(name: String): Flow<Resource<List<StoreLocationByNameModel>>>
}