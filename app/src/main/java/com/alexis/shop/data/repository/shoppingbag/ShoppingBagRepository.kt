package com.alexis.shop.data.repository.shoppingbag

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.shoppingbag.CartsItem
import com.alexis.shop.data.remote.shoppingbag.ShoppingBagRemoteDataSource
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.domain.repository.shoppingbag.IShoppingBagRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingBagRepository @Inject constructor(
    private val remoteDataSource: ShoppingBagRemoteDataSource
) : IShoppingBagRepository {

    override fun postShoppingBag(productId: String, userId: Int, quantity: Int, sizeId: String): Flow<Resource<ShoppingBagModel>> {
        return flow<Resource<ShoppingBagModel>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.postShoppingBag(productId, userId, quantity, sizeId).first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        ShoppingBagModel(
                            productId = apiResponse.data.productId.orEmpty(),
                            qty = apiResponse.data.qty.orZero(),
                            size = apiResponse.data.sizeId.orEmpty()
                        )
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun deleteShoppingBag(cardId: Int): Flow<Resource<String>> {
        return flow<Resource<String>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.deleteShoppingBag(cardId).first()) {
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getShoppingBag(userId: Int): Flow<Resource<List<ShoppingBagModel>>> {
        return flow<Resource<List<ShoppingBagModel>>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getShoppingBag(userId).first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateShoppingBagModel(apiResponse.data.data?.carts)
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateShoppingBagModel(data: List<CartsItem>?): List<ShoppingBagModel> {
        return data?.map {
            ShoppingBagModel(
                productId = it.productId.toString(),
                shoppingBagId = it.id.orZero(),
                qty = it.qty.orZero(),
                indonesiaName = it.indonesianName.orEmpty(),
                englishName = it.englishName.orEmpty(),
                price = it.price.orZero(),
                weight = it.weight.orZero(),
                imageUrl = it.imageUrl.orEmpty(),
                size = it.size.orEmpty()
            )
        } ?: listOf()
    }
}