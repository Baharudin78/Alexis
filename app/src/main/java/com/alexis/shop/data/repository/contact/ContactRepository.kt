package com.alexis.shop.data.repository.contact

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.ContactDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.contact.ContactData
import com.alexis.shop.data.remote.response.contact.ContactResponse
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.domain.model.contact.ContactModel
import com.alexis.shop.domain.repository.contact.IContactRepository
import com.alexis.shop.utils.orZero
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val contactDataSource: ContactDataSource
) : IContactRepository{

    override fun getContact(): Flow<Resource<ContactModel>> {
        return flow<Resource<ContactModel>> {
            emit(Resource.Loading())
            when(val apiResponse = contactDataSource.getContact().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(generateContactModel(apiResponse.data.data.items))
                )
                is ApiResponse.Empty -> listOf<ContactModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun generateContactModel (data : List<ContactData?>?) : ContactModel {
        return if ((!data.isNullOrEmpty())) {
            ContactModel(
                items = data.map {
                    ContactDataModel(
                        email = it?.email.orEmpty(),
                        id = it?.id.orZero(),
                        whatsapp = it?.whatsapp.orEmpty()
                    )
                }
            )
        }else{
            ContactModel()
        }
    }
}