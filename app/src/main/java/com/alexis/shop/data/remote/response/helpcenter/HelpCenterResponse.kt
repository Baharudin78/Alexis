package com.alexis.shop.data.remote.response.helpcenter

import com.google.gson.annotations.SerializedName

data class HelpCenterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: HelpCenterItemList,
    @SerializedName("error")
    val error: Any,
    @SerializedName("status")
    val status: String
)
data class HelpCenterItemList(
    @SerializedName("items")
    val items: List<HelpCenterItem>
)
data class HelpCenterItem(
    @SerializedName("help_center_detail")
    val helpCenterDetail: HelpCenterDetailItem?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
data class HelpCenterDetailItem(
    @SerializedName("items")
    val helpCenterDetail: List<HelpCenterDetail>
)
data class HelpCenterDetail(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("help_center_id")
    val helpCenterId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("question")
    val question: String,
    @SerializedName("choosec")
    val choosed : Boolean
)