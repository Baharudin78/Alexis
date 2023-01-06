package com.alexis.shop.domain.usecase.order

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.order.OrderListModel
import com.alexis.shop.domain.model.points.PointListModel
import kotlinx.coroutines.flow.Flow

interface OrderUseCase {
    fun getOrder() : Flow<Resource<OrderListModel>>
    fun getPointHistory() : Flow<Resource<PointListModel>>
}