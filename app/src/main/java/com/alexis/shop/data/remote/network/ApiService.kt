package com.alexis.shop.data.remote.network

import com.alexis.shop.data.remote.response.kota.CityResponse
import com.alexis.shop.data.remote.response.auth.LoginResponse
import com.alexis.shop.data.remote.response.auth.RegisterResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressGetResponse
import com.alexis.shop.data.remote.response.checkout.CheckoutAddressPostResponse
import com.alexis.shop.data.remote.response.productbaru.ProductBaruResponse
import com.alexis.shop.data.remote.response.voucher.VoucherResponse
import com.alexis.shop.data.remote.response.auth.LogoutResponse
import com.alexis.shop.data.remote.response.landing.LandingResponse
import com.alexis.shop.data.remote.response.product.ProductsGetByIdResponse
import com.alexis.shop.data.remote.response.product.category.ProductCategoryResponse
import com.alexis.shop.data.remote.response.product.category.subcategory.SubProductCategoryResponse
import com.alexis.shop.data.remote.response.shoppingbag.ShoppingBagDeleteResponse
import com.alexis.shop.data.remote.response.shoppingbag.ShoppingBagPostResponse
import com.alexis.shop.data.remote.response.shoppingbag.ShoppingBagResponse
import com.alexis.shop.data.remote.response.sizefilter.SizeFilterResponse
import com.alexis.shop.data.remote.response.storelocation.AllStoreLocationResponse
import com.alexis.shop.data.remote.response.storelocation.StoreLocationByNameResponse
import com.alexis.shop.data.remote.response.wishlist.WishlistGetResponse
import com.alexis.shop.data.remote.response.wishlist.WishlistPostResponse
import retrofit2.http.*

interface ApiService {

    @GET("mw/landing-image")
    suspend fun getLandingImage() : LandingResponse

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

    @POST("mw/auth/logout")
    suspend fun logOut() : LogoutResponse

    @POST(UrlConstant.ACTIVATE_URL)
    @FormUrlEncoded
    suspend fun activateUser(@Field("email") email: String): Any

    @POST("mw/customer-cart")
    @FormUrlEncoded
    suspend fun postShoppingBag(
        @Field("product_item_code") productItemCode: String,
        @Field("product_size_id") productSizeId: String,
        @Field("unit") unit: String,
    ): ShoppingBagPostResponse

    @GET("mw/customer-cart")
    suspend fun getShoppingBag(): ShoppingBagResponse

    @DELETE(UrlConstant.SHOPPING_BAG_URL)
    suspend fun deleteShoppingBag(@Query("cart_id") cartId: Int): ShoppingBagDeleteResponse

    @POST("mw/wishlist")
    @FormUrlEncoded
    suspend fun postWishlist(
        @Field("product_item_code") productItemCode: String
    ): WishlistPostResponse

    @GET("mw/wishlist")
    suspend fun getWishlist(
    ): WishlistGetResponse

    @DELETE(UrlConstant.WISHLIST_URL)
    suspend fun deleteWishlist(@Query("wishlist_id") wishlistId: Int): WishlistGetResponse

    @GET("mw/products/")
    suspend fun getAllProduct(): ProductBaruResponse

    @GET("mw/product/{id}/detail")
    suspend fun getProductById(@Path("id") productId: Int): ProductsGetByIdResponse

    @GET("mw/product-category")
    suspend fun getAllProductCategory(): ProductCategoryResponse

    @GET("mw/product-subcategory")
    suspend fun getSubProductCategory(
        @Query("name") name : String
    ) : SubProductCategoryResponse

    @GET("mw/store-location")
    suspend fun getStoreHome() : AllStoreLocationResponse

    @GET("mw/store-location")
    suspend fun getAllStoreLocation(): AllStoreLocationResponse

    @GET("mw/store-location")
    suspend fun getStoreLocationByName(@Query("province") name: String): StoreLocationByNameResponse


    @GET("mw/product-size/")
    suspend fun getSizeFilter(): SizeFilterResponse

    @GET("mw/voucher")
    suspend fun getVoucher(): VoucherResponse

    /*
    ADDRESS
     */

    @GET("mw/ref-address")
    suspend fun getKelurahan(
        @Header("Authorization")token : String,
        @Query("name") name : String
    ) : CityResponse

    @GET("mw/address")
    suspend fun getCheckOutAddress(
        @Header("Authorization") token: String
    ): CheckoutAddressGetResponse

    @POST("mw/address")
    @FormUrlEncoded
    suspend fun postCheckOutAddress(
        @Header("Authorization") token : String,
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