package com.alexis.shop.data.remote.response.helpcenter

import com.google.gson.annotations.SerializedName

data class HelpCenterResponse(
    val code: Int,
    val `data`: HelpCenterItemList,
    val error: Any,
    val status: String
)
data class HelpCenterItemList(
    val items: List<HelpCenterItem>
)
data class HelpCenterItem(
    @field:SerializedName("help_center_detail")
    val helpCenterDetail: HelpCenterDetailItem?,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String
)
data class HelpCenterDetailItem(
    @field:SerializedName("items")
    val helpCenterDetail: List<HelpCenterDetail>
)
data class HelpCenterDetail(
    @field:SerializedName("answer")
    val answer: String,
    @field:SerializedName("help_center_id")
    val helpCenterId: Int,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("question")
    val question: String,
    val choosed : Boolean
)