package com.alexis.shop.domain.repository.order

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.order.OrderListModel
import com.alexis.shop.domain.model.points.PointListModel
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    fun getOrder() : Flow<Resource<OrderListModel>>
    fun getPoint() : Flow<Resource<PointListModel>>
}