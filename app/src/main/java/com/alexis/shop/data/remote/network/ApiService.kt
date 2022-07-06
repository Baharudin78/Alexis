package com.alexis.shop.data.remote.network

import com.alexis.shop.data.remote.auth.LoginResponse
import com.alexis.shop.data.remote.auth.RegisterResponse
import com.alexis.shop.data.remote.checkout.CheckoutAddressGetResponse
import com.alexis.shop.data.remote.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.model.productbaru.ProductBaruResponse
import com.alexis.shop.data.remote.product.ProductsGetByIdResponse
import com.alexis.shop.data.remote.product.category.ProductCategoryResponse
import com.alexis.shop.data.remote.product.category.subcategory.SubProductCategoryResponse
import com.alexis.shop.data.remote.shoppingbag.ShoppingBagDeleteResponse
import com.alexis.shop.data.remote.shoppingbag.ShoppingBagPostResponse
import com.alexis.shop.data.remote.shoppingbag.ShoppingBagResponse
import com.alexis.shop.data.remote.sizefilter.SizeFilterResponse
import com.alexis.shop.data.remote.storelocation.AllStoreLocationResponse
import com.alexis.shop.data.remote.storelocation.StoreLocationByNameResponse
import com.alexis.shop.data.remote.wishlist.WishlistGetResponse
import com.alexis.shop.data.remote.wishlist.WishlistPostResponse
import retrofit2.http.*

interface ApiService {
    @POST("mw/auth/register")
    @FormUrlEncoded
    suspend fun registration(
        @Field("nama_lengkap") name: String,
        @Field("no_telp") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("tanggal_lahir") tanggalLahir: String
    ): RegisterResponse

    @POST("mw/auth/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @POST(UrlConstant.ACTIVATE_URL)
    @FormUrlEncoded
    suspend fun activateUser(@Field("email") email: String): Any

    @POST(UrlConstant.SHOPPING_BAG_URL)
    @FormUrlEncoded
    suspend fun postShoppingBag(
        @Field("product_id") productId: String,
        @Field("user_id") userId: Int,
        @Field("unit") unit: Int,
        @Field("size_id") sizeId: String
    ): ShoppingBagPostResponse

    @GET(UrlConstant.SHOPPING_BAG_URL)
    suspend fun getShoppingBag(@Query("user_id") userId: Int): ShoppingBagResponse

    @DELETE(UrlConstant.SHOPPING_BAG_URL)
    suspend fun deleteShoppingBag(@Query("cart_id") cartId: Int): ShoppingBagDeleteResponse

    @POST(UrlConstant.WISHLIST_URL)
    @FormUrlEncoded
    suspend fun postWishlist(
        @Field("product_id") productId: String,
        @Field("user_id") userId: Int
    ): WishlistPostResponse

    @GET("mw/wishlist/customer/{user_id}")
    suspend fun getWishlist(@Query("customer_id") userId: Int): WishlistGetResponse

    @DELETE(UrlConstant.WISHLIST_URL)
    suspend fun deleteWishlist(@Query("wishlist_id") wishlistId: Int): WishlistGetResponse

    @GET("mw/products/")
    //suspend fun getAllProduct(): ProductsResponse
    suspend fun getAllProduct(): ProductBaruResponse

    @GET("mw/products/{productId}")
    suspend fun getProductById(@Query("product_id") productId: Int): ProductsGetByIdResponse

    @GET("mw/product-category")
    suspend fun getAllProductCategory(): ProductCategoryResponse

    suspend fun getSubProductCategory() : SubProductCategoryResponse

    @GET("mw/store-location")
    suspend fun getAllStoreLocation(): AllStoreLocationResponse

    @GET("mw/store-location")
    suspend fun getStoreLocationByName(@Query("province") name: String): StoreLocationByNameResponse

    @GET("mw/product-size/")
    suspend fun getSizeFilter(): SizeFilterResponse

    @GET(UrlConstant.VOUCHER)
    suspend fun getVoucher(): SizeFilterResponse

    @GET(UrlConstant.CHECKOUT_ADDRESS)
    suspend fun getCheckOutAddress(@Query("user_id") userId: Int): CheckoutAddressGetResponse

    @POST(UrlConstant.CHECKOUT_ADDRESS)
    @FormUrlEncoded
    suspend fun postCheckOutAddress(
        @Field("type_address") typeAddress: String,
        @Field("user_id") userId: Int,
        @Field("recipient_name") recipientName: String,
        @Field("address") address: String,
        @Field("other_detail") otherDetail: String,
        @Field("postal_code") postalCode: Int,
        @Field("recipient_phone_number") recipientPhoneNumber: String,
        @Field("is_default") isDefault: Boolean,
        @Field("as_dropship") asDropship: Boolean
    ): CheckoutAddressPostResponse
}