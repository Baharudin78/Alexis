package com.alexis.shop.data.remote.response.point

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class PointResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: PointItemList,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)

data class PointItemList(
    @SerializedName("item")
    val item: List<PointsItem>
)

data class PointsItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("customer")
    val customer: CustomerPointItem,
    @SerializedName("customer_id")
    val customer_id: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: Int,
    @SerializedName("updateAt")
    val updatedAt: String
)

data class CustomerPointItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_blacklist")
    val is_blacklist: Int,
    @SerializedName("kode_referal")
    val kode_referal: Any,
    @SerializedName("nama_lengkap")
    val nama_lengkap: String,
    @SerializedName("no_telp")
    val no_telp: String,
    @SerializedName("tanggal_lahir")
    val tanggal_lahir: String,
    @SerializedName("total_poin")
    val total_poin: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val user_id: Int
)