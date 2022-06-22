package com.alexis.shop.data.repository.wishlist

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.wishlist.WishlistItem
import com.alexis.shop.data.remote.wishlist.WishlistRemoteDataSource
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRepository @Inject constructor(
    private val remoteDataSource: WishlistRemoteDataSource
) : IWishlistRepository {

    override fun postWishlist(productDetailCode: String, userId: Int): Flow<Resource<String>> {
        return flow<Resource<String>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postWishlist(productDetailCode, userId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.data?.productId.orEmpty()))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getWishlist(userId: Int): Flow<Resource<List<WishlistModel>>> {
        return flow<Resource<List<WishlistModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getWishlist(userId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateWishlistGetModel(apiResponse.data.data?.wishlist)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateWishlistGetModel(data: List<WishlistItem>?): List<WishlistModel> {
        return data?.map {
            WishlistModel(
                updatedAt = it.updatedAt.orEmpty(),
                productId = it.productId.orZero(),
                createdAt = it.createdAt.orEmpty(),
                id = it.id.orZero(),
                customerId = it.customerId.orZero(),
                indonesiaName = it.indonesiaName.orEmpty(),
                englishName = it.englishName.orEmpty(),
                price = it.price.orZero(),
                weight = it.weight.orZero(),
                qty = it.qty.orZero(),
                imageUrl = it.imageUrl.orEmpty()
            )
        } ?: listOf()
    }
}