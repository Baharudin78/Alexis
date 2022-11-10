package com.alexis.shop.domain.repository.helpcenter

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterItemList
import com.alexis.shop.domain.model.helpcenter.HelpCenterDetailModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterModel
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import kotlinx.coroutines.flow.Flow

interface IHelpCenterRepository {
    fun getHelpCenter(): Flow<Resource<HelpCenterModel>>
    fun getFAQHelp() : Flow<Resource<List<HelpCenterDetailModel>>>
}