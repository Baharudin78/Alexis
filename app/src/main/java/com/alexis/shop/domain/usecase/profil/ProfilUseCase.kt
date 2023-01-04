package com.alexis.shop.domain.usecase.profil

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.profil.ProfilModel
import kotlinx.coroutines.flow.Flow

interface ProfilUseCase {
    fun getProfil() : Flow<Resource<ProfilModel>>
    fun updateName(name : String): Flow<Resource<ProfilModel>>
    fun updatePhone(phone : String) : Flow<Resource<ProfilModel>>
}