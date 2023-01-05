package com.alexis.shop.data.repository.profil

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.ProfilDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.profil.Profil
import com.alexis.shop.data.remote.response.wishlist.delete.MessageResponse
import com.alexis.shop.domain.model.profil.ProfilModel
import com.alexis.shop.domain.repository.IProfilRepository
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilRepository @Inject constructor(
    private val profilDataSource: ProfilDataSource
) : IProfilRepository{

    override fun getProfil(): Flow<Resource<ProfilModel>> {
        return flow<Resource<ProfilModel>> {
            emit(Resource.Loading())
            when(val apiResponse = profilDataSource.getProfile().first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateProfilModel(apiResponse.data.data.item)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun updateName(name: String): Flow<Resource<ProfilModel>> {
        return flow<Resource<ProfilModel>> {
            emit(Resource.Loading())
            when(val apiResponse = profilDataSource.updateName(name).first()){
                is ApiResponse.Success -> emit(Resource.Success(generateProfilModel(apiResponse.data.data.item)))
                is ApiResponse.Empty ->{}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun updateNoTelp(phone: String, password : String): Flow<Resource<ProfilModel>> {
        return flow<Resource<ProfilModel>>{
            emit(Resource.Loading())
            when(val apiResponse = profilDataSource.updateNoPhone(phone, password).first()) {
                is ApiResponse.Success -> emit(Resource.Success(generateProfilModel(apiResponse.data.data.item)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }

        }
    }

    override fun updateEmail(email: String, password: String): Flow<Resource<ProfilModel>> {
        return flow<Resource<ProfilModel>>{
            emit(Resource.Loading())
            when(val apiResponse = profilDataSource.updateEmail(email, password).first()){
                is ApiResponse.Success -> emit(Resource.Success(generateProfilModel(apiResponse.data.data.item)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun updateTanggal(tanggal: String): Flow<Resource<ProfilModel>> {
        return flow<Resource<ProfilModel>>{
            emit(Resource.Loading())
            when(val apiResponse = profilDataSource.updateTanggalLahir(tanggal).first()){
                is ApiResponse.Success -> emit(Resource.Success(generateProfilModel(apiResponse.data.data.item)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }


    private fun generateProfilModel(profil : Profil?) : ProfilModel {
        return if (profil != null) {
            ProfilModel(
                email = profil.email,
                id = profil.id,
                is_blacklist = profil.is_blacklist,
                kode_referal = profil.kode_referal,
                nama_lengkap = profil.nama_lengkap,
                no_telp = profil.no_telp,
                registration_date = profil.registration_date,
                tanggal_lahir = profil.tanggal_lahir,
                total_poin = profil.total_poin,
                user_id = profil.user_id
            )
        }else {
            ProfilModel()
        }
    }
}