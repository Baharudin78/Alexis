package com.alexis.shop.domain.repository

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.profil.ProfilModel
import kotlinx.coroutines.flow.Flow

interface IProfilRepository {
    fun getProfil() : Flow<Resource<ProfilModel>>
    fun updateName(name : String): Flow<Resource<ProfilModel>>
}