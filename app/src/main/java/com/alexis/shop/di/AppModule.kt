package com.alexis.shop.di

import com.alexis.shop.domain.usecase.auth.AuthInteractor
import com.alexis.shop.domain.usecase.auth.AuthUseCase
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressInteractor
import com.alexis.shop.domain.usecase.checkout.CheckoutAddressUseCase
import com.alexis.shop.domain.usecase.city.CityInteractor
import com.alexis.shop.domain.usecase.city.CityUseCase
import com.alexis.shop.domain.usecase.helpcenter.HelpCenterInteractor
import com.alexis.shop.domain.usecase.helpcenter.HelpCenterUseCase
import com.alexis.shop.domain.usecase.landing.LandingInteractor
import com.alexis.shop.domain.usecase.landing.LandingUseCase
import com.alexis.shop.domain.usecase.product.ProductInteractor
import com.alexis.shop.domain.usecase.product.ProductUseCase
import com.alexis.shop.domain.usecase.product.category.ProductCategoryInteractor
import com.alexis.shop.domain.usecase.product.category.ProductCategoryUseCase
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagInteractor
import com.alexis.shop.domain.usecase.shoppingbag.ShoppingBagUseCase
import com.alexis.shop.domain.usecase.sizefilter.SizeFilterInteractor
import com.alexis.shop.domain.usecase.sizefilter.SizeFilterUseCase
import com.alexis.shop.domain.usecase.storelocation.StoreLocationInteractor
import com.alexis.shop.domain.usecase.storelocation.StoreLocationUseCase
import com.alexis.shop.domain.usecase.voucher.VoucherInteractor
import com.alexis.shop.domain.usecase.voucher.VoucherUseCase
import com.alexis.shop.domain.usecase.wishlist.WishlistInteractor
import com.alexis.shop.domain.usecase.wishlist.WishlistUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideAuthUseCase(interactor: AuthInteractor): AuthUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideWishlistUseCase(interactor: WishlistInteractor): WishlistUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideShoppingBagUseCase(interactor: ShoppingBagInteractor): ShoppingBagUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideProductUseCase(interactor: ProductInteractor): ProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideProductCategoryUseCase(interactor: ProductCategoryInteractor): ProductCategoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideStoreLocationUseCase(interactor: StoreLocationInteractor): StoreLocationUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideSizeFilterUseCase(interactor: SizeFilterInteractor): SizeFilterUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCheckoutAddressUseCase(interactor: CheckoutAddressInteractor): CheckoutAddressUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideVoucherUseCase(interactor : VoucherInteractor) : VoucherUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCityUseCase(interactor : CityInteractor) : CityUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideLandingUseCase(interactor : LandingInteractor) : LandingUseCase

    @Binds
    @ViewModelScoped
    abstract fun prvideHelpCenterUseCase(interactor : HelpCenterInteractor) : HelpCenterUseCase
}