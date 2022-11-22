package com.alexis.shop.domain.repository.contact

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.domain.model.contact.ContactModel
import kotlinx.coroutines.flow.Flow

interface IContactRepository {
    fun getContact() : Flow<Resource<ContactDataModel>>
}