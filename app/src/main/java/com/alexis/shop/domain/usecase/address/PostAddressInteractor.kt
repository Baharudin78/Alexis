package com.alexis.shop.domain.usecase.address

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PostAddressInteractor @Inject constructor(
    val postAddressUseCase: PostAddressUseCase
)