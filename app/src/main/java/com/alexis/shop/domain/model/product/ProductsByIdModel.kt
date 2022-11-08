package com.alexis.shop.domain.model.product

data class ProductsByIdModel(
    val id: Int? = null ,
    val barcode : String? = null,
    val stockKeepingUnit : String? = null,
    val itemCode : String? = null,
    val productName: String? = null,
    val productSubcategoryId : String? = null,
    val stock : Int? = null,
    val price : Int? = null,
    val weight : Int? = null,
    val styleCode : String? = null,
    val productMaterialId : String? = null,
    val colorCode : String? = null,
    val productSizeId : String? = null,
    val packagingId : String? = null,
    val status : String? = null,
    val storeLocationId : Int? = null,
    val userId : Int? = null,
    val images: MutableList<ProductsGetByIdImagesModel> = mutableListOf(),
    val productMaterial : ProductsGetByIdMaterialModel? = null,
    val productSize : ProductsGetByIdSizeModel? = null,
    val productSizeList: MutableList<ProductsGetByIdSizeModel> = mutableListOf(),
    val exclusiveOffer: ProductExclusiveOffer? = null,
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
