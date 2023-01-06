package com.alexis.shop.domain.model.points

import com.alexis.shop.data.remote.response.point.CustomerPointItem
import com.google.gson.annotations.SerializedName


data class PointListModel(
    var points : List<PointItemModel> = mutableListOf()
)

data class PointItemModel(
    val createdAt: String,
    val customer: CustomerItemModel,
    val customer_id: Int,
    val id: Int,
    val name: String,
    val point: Int,
    val updatedAt: String
)

data class CustomerItemModel(
    val createdAt: String,
    val email: String,
    val id: Int,
    val isBlacklist: Int,
    val kodeReferal: Any,
    val namaLengkap: String,
    val noTelp: String,
    val tanggalLahir: String,
    val totalPoin: Int,
    val updatedAt: String,
    val userId: Int
)