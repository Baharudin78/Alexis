package com.alexis.shop.data.remote.response.landing

import com.alexis.shop.ui.detail.adapter.viewholder.LandingPageVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory
import com.google.gson.annotations.SerializedName

data class LandingResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: LandingDataItem? = null,
    @SerializedName("error")
    val error: Any?= null,
    @SerializedName("status")
    val status: String? = null
)
data class LandingDataItem(
    @SerializedName("item")
    val landingItem : LandingItem? = null
)

data class LandingItem(
    @SerializedName("mobile_landing_image")
    val mobileLandingImage: String? = null,
    @SerializedName("desktop_landing_image")
    val desktopLandingImage: String? = null,
    @SerializedName("end_date")
    val endDate: String? = null,
    @SerializedName("hyperlink")
    val hyperlink: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_color_mobile")
    val logoColorMobile: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("start_date")
    val startDate: String? = null,
    val layoutHeight: Int = 0
): BaseItemModel() {
    override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
        typeVH = LandingPageVH.LAYOUT
        return typeFactoryBase.type(this)
    }
}