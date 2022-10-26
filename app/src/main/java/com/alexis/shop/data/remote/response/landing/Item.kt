package com.alexis.shop.data.remote.response.landing

data class Item(
    val desktop_landing_image: String,
    val end_date: String,
    val hyperlink: String,
    val id: Int,
    val logo_color_desktop: String,
    val logo_color_mobile: String,
    val mobile_landing_image: String,
    val name: String,
    val start_date: String
)