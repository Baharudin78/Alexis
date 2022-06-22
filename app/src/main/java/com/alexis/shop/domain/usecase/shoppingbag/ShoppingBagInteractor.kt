package com.alexis.shop.domain.usecase.shoppingbag

import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.domain.repository.shoppingbag.IShoppingBagRepository
import com.alexis.shop.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingBagInteractor @Inject constructor(private val repository: IShoppingBagRepository): ShoppingBagUseCase {
    override fun postShoppingBag(productId: String, userId: Int, quantity: Int, sizeId: String): Flow<Resource<ShoppingBagModel>> {
        return repository.postShoppingBag(productId, userId, quantity, sizeId)
    }

    override fun deleteShoppingBag(cardId: Int): Flow<Resource<String>> {
        return repository.deleteShoppingBag(cardId)
    }

    override fun getShoppingBag(userId: Int): Flow<Resource<List<ShoppingBagModel>>> {
        return repository.getShoppingBag(userId)
    }
}