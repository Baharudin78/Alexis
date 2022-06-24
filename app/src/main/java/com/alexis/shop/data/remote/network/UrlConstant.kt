package com.alexis.shop.data.remote.network

object UrlConstant {
    private const val AUTH_URL = "auth/"
    const val LOGIN_URL = AUTH_URL + "login"
    const val REGISTER_URL = AUTH_URL + "register"
    const val ACTIVATE_URL = AUTH_URL + "activate"

    const val PRODUCTS_URL = "mw/products/"
    const val CATEGORY_URL = PRODUCTS_URL + "category"
    const val SIZE_FILTER_URL = PRODUCTS_URL + "size-code"
    const val SHOPPING_BAG_URL = PRODUCTS_URL + "cart"
    const val WISHLIST_URL = PRODUCTS_URL + "wishlist"

    private const val BASE_STORE_URL = "stores/"
    const val STORE_LOCATION_URL = BASE_STORE_URL + "location"

    const val VOUCHER = "vouchers"

    const val CHECKOUT_ADDRESS = "checkout/address"
}