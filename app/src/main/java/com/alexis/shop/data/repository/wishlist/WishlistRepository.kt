package com.alexis.shop.data.repository.wishlist

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.wishlist.WishlistItem
import com.alexis.shop.data.remote.datasource.WishlistRemoteDataSource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRepository @Inject constructor(
    private val remoteDataSource: WishlistRemoteDataSource
) : IWishlistRepository {

    override fun postWishlist( productItemCode: String): Flow<Resource<String>> {
        return flow<Resource<String>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postWishlist(productItemCode).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.data?.postWishList?.customeId.orEmpty()))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getWishlist(): Flow<Resource<List<WishlistModel>>> {
        return flow<Resource<List<WishlistModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getWishlist().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateWishlistGetModel(apiResponse.data.data?.wishlist)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getWishlistBySize(sizeId: Int): Flow<Resource<List<WishlistModel>>> {
        return flow<Resource<List<WishlistModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getWishlistBySize(sizeId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateWishlistGetModel(apiResponse.data.data?.wishlist)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun deleteWishlist(id: Int): Flow<Resource<MessageResponse>> {
        return flow<Resource<MessageResponse>> {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.deleteWishlist(id).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateWishlistGetModel(data: List<WishlistItem>?): List<WishlistModel> {
        return data?.map {
            WishlistModel(
                id = it.id,
                customerId = it.customer_id,
                productItemCode = it.product_item_code,
                product = it.product
            )
        } ?: listOf()
    }
}