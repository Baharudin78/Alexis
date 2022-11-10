package com.alexis.shop.domain.usecase.helpcenter

import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.helpcenter.HelpCenterDetailModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterModel
import kotlinx.coroutines.flow.Flow

interface HelpCenterUseCase {
    fun getHelpCenterUseCase() : Flow<Resource<HelpCenterModel>>
    fun getFAQHelp() : Flow<Resource<List<HelpCenterDetailModel>>>
}