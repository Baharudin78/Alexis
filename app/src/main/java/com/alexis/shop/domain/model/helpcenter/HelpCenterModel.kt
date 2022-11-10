package com.alexis.shop.domain.model.helpcenter

data class HelpCenterModel(
    var helpCenter : List<HelpCenterItemModel> = mutableListOf()
)
data class HelpCenterItemModel(
    val helpCenterDetail: List<HelpCenterDetailModel>,
    val id: Int,
    val name: String
)

data class HelpCenterDetailList(
    val helpListDetail : List<HelpCenterDetailModel> = mutableListOf()
)
data class HelpCenterDetailModel(
    val answer: String,
    val helpCenterId: Int,
    val id: Int,
    val question: String,
    val choosed : Boolean
)