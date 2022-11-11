package com.alexis.shop.domain.usecase.contact

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.contact.ContactRepository
import com.alexis.shop.domain.model.contact.ContactModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactInteractor @Inject constructor(
    private val contactRepository: ContactRepository
) : ContactUseCase{
    override fun getContact(): Flow<Resource<ContactModel>> {
        return contactRepository.getContact()
    }
}