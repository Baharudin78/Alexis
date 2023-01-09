package com.alexis.shop.domain.usecase.shoppingbag

import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.shoppingbag.ShopingBagListModel
import com.alexis.shop.domain.model.shoppingbag.ShopingBagPostModel
import kotlinx.coroutines.flow.Flow

interface ShoppingBagUseCase {
    fun postShoppingBag(productItemCode : String, productSizeId : String, quantity : String): Flow<Resource<ShopingBagPostModel>>
    fun deleteShoppingBag(cardId: Int): Flow<Resource<MessageResponse>>
    fun getShoppingBag(): Flow<Resource<ShopingBagListModel>>
    fun getShipping(): Flow<Resource<ShopingBagListModel>>
}