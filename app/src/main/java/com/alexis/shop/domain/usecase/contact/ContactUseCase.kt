package com.alexis.shop.domain.usecase.contact

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.domain.model.contact.ContactModel
import kotlinx.coroutines.flow.Flow

interface ContactUseCase {
    fun getContact() : Flow<Resource<ContactDataModel>>
}