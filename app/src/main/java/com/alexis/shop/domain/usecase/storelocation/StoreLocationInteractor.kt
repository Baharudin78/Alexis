package com.alexis.shop.domain.usecase.storelocation

import com.alexis.shop.domain.model.store_location.AllStoreLocationModel
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.domain.repository.storelocation.IStoreLocationRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreLocationInteractor @Inject constructor(private val repository: IStoreLocationRepository) :
    StoreLocationUseCase {
    override fun getAllStoreLocation(): Flow<Resource<AllStoreLocationModel>> {
        return repository.getAllStoreLocation()
    }

    override fun getStoreHome(): Flow<Resource<AllStoreLocationModel>> {
        return repository.getStoreHome()
    }

    override fun getStoreLocationByName(name: String): Flow<Resource<List<StoreLocationByNameModel>>> {
        return repository.getStoreLocationByName(name)
    }
}