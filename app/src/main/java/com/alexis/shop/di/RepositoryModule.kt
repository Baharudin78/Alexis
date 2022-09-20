package com.alexis.shop.di

import com.alexis.shop.data.repository.auth.AuthRepository
import com.alexis.shop.data.repository.checkout.CheckoutAddressRepository
import com.alexis.shop.data.repository.city.CityRepository
import com.alexis.shop.data.repository.product.ProductCategoryRepository
import com.alexis.shop.data.repository.product.ProductRepository
import com.alexis.shop.data.repository.shoppingbag.ShoppingBagRepository
import com.alexis.shop.data.repository.sizefilter.SizeFilterRepository
import com.alexis.shop.data.repository.storelocation.StoreLocationRepository
import com.alexis.shop.data.repository.wishlist.WishlistRepository
import com.alexis.shop.domain.repository.auth.IAuthRepository
import com.alexis.shop.domain.repository.checkout.ICheckoutAddressRepository
import com.alexis.shop.domain.repository.city.ICityRepository
import com.alexis.shop.domain.repository.product.IProductCategoryRepository
import com.alexis.shop.domain.repository.product.IProductRepository
import com.alexis.shop.domain.repository.shoppingbag.IShoppingBagRepository
import com.alexis.shop.domain.repository.sizefilter.ISizeFilterRepository
import com.alexis.shop.domain.repository.storelocation.IStoreLocationRepository
import com.alexis.shop.domain.repository.wishlist.IWishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(repository: AuthRepository): IAuthRepository

    @Binds
    abstract fun provideWishlistRepository(repository: WishlistRepository): IWishlistRepository

    @Binds
    abstract fun provideShoppingBagRepository(repository: ShoppingBagRepository): IShoppingBagRepository

    @Binds
    abstract fun provideProductRepository(repository: ProductRepository): IProductRepository

    @Binds
    abstract fun provideProductCategoryRepository(repository: ProductCategoryRepository): IProductCategoryRepository

    @Binds
    abstract fun provideStoreLocationRepository(repository: StoreLocationRepository): IStoreLocationRepository

    @Binds
    abstract fun provideSizeFilterRepository(repository: SizeFilterRepository): ISizeFilterRepository

    @Binds
    abstract fun provideCheckoutAddressRepository(repository: CheckoutAddressRepository): ICheckoutAddressRepository

    @Binds
    abstract fun provideCityRepository(repository : CityRepository) : ICityRepository
}