package com.alexis.shop.domain.repository.shoppingbag

import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow

interface IShoppingBagRepository {
    fun postShoppingBag(productId: String, userId: Int, quantity: Int, sizeId: String): Flow<Resource<ShoppingBagModel>>
    fun deleteShoppingBag(cardId: Int): Flow<Resource<String>>
    fun getShoppingBag(userId: Int): Flow<Resource<List<ShoppingBagModel>>>
}