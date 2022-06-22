package com.alexis.shop.domain.model.menu

data class MenuModel(
    val id: Int,
    val reverseId: Int,
    val icon: Int,
    val title: String,
    var isChoosed: Boolean = false,
    var isOpen: Boolean = false,
    var isClose: Boolean = false
)