package com.alexis.shop.data.remote.response.landing

import com.alexis.shop.ui.detail.adapter.viewholder.LandingPageVH
import com.dizcoding.mylibrv.BaseItemModel
import com.dizcoding.mylibrv.BaseItemTypeFactory
import com.google.gson.annotations.SerializedName

data class LandingResponse(
    @field:SerializedName("code")
    val code: Int? = null,
    @field:SerializedName("data")
    val `data`: LandingDataItem? = null,
    @field:SerializedName("error")
    val error: Any?= null,
    @field:SerializedName("status")
    val status: String? = null
)
data class LandingDataItem(
    @field:SerializedName("item")
    val landingItem : LandingItem? = null
)

data class LandingItem(
    @field:SerializedName("mobile_landing_image")
    val mobileLandingImage: String? = null,
    @field:SerializedName("desktop_landing_image")
    val desktopLandingImage: String? = null,
    @field:SerializedName("end_date")
    val endDate: String? = null,
    @field:SerializedName("hyperlink")
    val hyperlink: String? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("logo_color_mobile")
    val logoColorMobile: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("start_date")
    val startDate: String? = null,
    val layoutHeight: Int = 0
): BaseItemModel() {
    override fun type(typeFactoryBase: BaseItemTypeFactory): Int {
        typeVH = LandingPageVH.LAYOUT
        return typeFactoryBase.type(this)
    }
}