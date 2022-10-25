package com.alexis.shop.domain.model.product

data class ProductsByIdModel(
    val images: MutableList<ProductsGetByIdImagesModel> = mutableListOf(),
    val categoryName: String = "",
    val updatedAt: String = "",
    val size: MutableList<ProductsGetByIdSizeModel> = mutableListOf(),
 //   val material: MutableList<ProductsGetByIdMaterialModel> = mutableListOf(),
    val price: Int = 0,
    val exclusiveOffer: ProductExclusiveOffer? = null,
    val productId: Int = 0,
    val weight: Int = 0,
    val createdAt: String = "",
    val id: Int = 0,
    val stock: Int = 0,
    val productName: String = ""
)

data class ProductsGetByIdImagesModel(
    val image: String = "",
    val type: String = ""
)

data class ProductsGetByIdSizeModel(
    val name: String = "",
    val id: String = "",
    var isSelected: Boolean = false
)

data class ProductsGetByIdMaterialModel(
    val name: String = "",
    val id: String = ""
)

data class ProductExclusiveOffer(
    val id : Int = 0,
    val productItemCode : String = "",
    val redemptionPoint : Int = 0,
    val aging : Int = 0,
    val registrationDate :String = ""
)
