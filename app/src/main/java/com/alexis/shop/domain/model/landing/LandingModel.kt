package com.alexis.shop.domain.model.landing

import com.google.gson.annotations.SerializedName

data class LandingModel(
    val landingModel : LandingModelItem? = null
)

data class LandingModelItem(
    val desktopLandingImage: String? = null,
    val endDate: String? = null,
    val hyperlink: String? = null,
    val id: Int? = null,
    val logoColorMobile: String? = null,
    val mobileLandingImage: String? = null,
    val name: String? = null,
    val startDate: String? = null
)
