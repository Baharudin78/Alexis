package com.alexis.shop.domain.usecase.order

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.order.OrderRepository
import com.alexis.shop.domain.model.order.OrderListModel
import com.alexis.shop.domain.model.points.PointListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderIntactor @Inject constructor(
    private val resository : OrderRepository
): OrderUseCase{
    override fun getOrder(): Flow<Resource<OrderListModel>> {
        return resository.getOrder()
    }

    override fun getPointHistory(): Flow<Resource<PointListModel>> {
        return resository.getPoint()
    }
}