package com.alexis.shop.domain.usecase.helpcenter

import com.alexis.shop.data.Resource
import com.alexis.shop.data.repository.helpcenter.HelpCenterRepository
import com.alexis.shop.domain.model.helpcenter.HelpCenterDetailModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HelpCenterInteractor @Inject constructor(
    private val helpCenterRepository: HelpCenterRepository
): HelpCenterUseCase{
    override fun getHelpCenterUseCase(): Flow<Resource<HelpCenterModel>> {
        return helpCenterRepository.getHelpCenter()
    }

    override fun getFAQHelp(): Flow<Resource<List<HelpCenterDetailModel>>> {
        return helpCenterRepository.getFAQHelp()
    }
}