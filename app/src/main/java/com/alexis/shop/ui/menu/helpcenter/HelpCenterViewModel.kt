package com.alexis.shop.ui.menu.helpcenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alexis.shop.domain.usecase.helpcenter.HelpCenterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HelpCenterViewModel @Inject constructor(
    private val helpCenterUseCase: HelpCenterUseCase
) : ViewModel() {
    fun getHelpCenter() = helpCenterUseCase.getHelpCenterUseCase().asLiveData()
    fun getFAQHelp() = helpCenterUseCase.getFAQHelp().asLiveData()
}