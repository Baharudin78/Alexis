package com.alexis.shop.ui.menu.contactus

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.contact.ContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactUseCase: ContactUseCase
) : ViewModel(){

    fun getContact() = contactUseCase.getContact().asLiveData()
}