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

    override fun updatePhone(phone: String, password : String): Flow<Resource<ProfilModel>> {
        return repository.updateNoTelp(phone, password)
    }

    override fun updateEmail(email: String, password: String): Flow<Resource<ProfilModel>> {
        return repository.updateEmail(email, password)
    }

    override fun updateTanggal(tanggal: String): Flow<Resource<ProfilModel>> {
        return repository.updateTanggal(tanggal)
    }
}