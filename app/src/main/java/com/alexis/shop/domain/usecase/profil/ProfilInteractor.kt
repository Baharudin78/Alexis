package com.alexis.shop.domain.usecase.profil

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.profil.ProfilModel
import com.alexis.shop.domain.repository.IProfilRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfilInteractor @Inject constructor(
    private var repository : IProfilRepository
) : ProfilUseCase{
    override fun getProfil(): Flow<Resource<ProfilModel>> {
        return repository.getProfil()
    }

    override fun updateName(name: String): Flow<Resource<ProfilModel>> {
        return repository.updateName(name)
    }
}