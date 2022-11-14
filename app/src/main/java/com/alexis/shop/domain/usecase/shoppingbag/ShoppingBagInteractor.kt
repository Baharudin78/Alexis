package com.alexis.shop.domain.usecase.shoppingbag

import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.domain.repository.shoppingbag.IShoppingBagRepository
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.shoppingbag.ShopingBagListModel
import com.alexis.shop.domain.model.shoppingbag.ShopingBagPostModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingBagInteractor @Inject constructor(private val repository: IShoppingBagRepository): ShoppingBagUseCase {
    override fun postShoppingBag(productItemCode : String, productSizeId : String, quantity : String): Flow<Resource<ShopingBagPostModel>> {
        return repository.postShoppingBag(productItemCode, productSizeId, quantity)
    }

    override fun deleteShoppingBag(cardId: Int): Flow<Resource<String>> {
        return repository.deleteShoppingBag(cardId)
    }

    override fun getShoppingBag(): Flow<Resource<ShopingBagListModel>> {
        return repository.getShoppingBag()
    }
}