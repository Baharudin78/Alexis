package com.alexis.shop.data.repository.helpcenter

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.HelpCenterRemoteDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterDetail
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterDetailItem
import com.alexis.shop.data.remote.response.helpcenter.HelpCenterItem
import com.alexis.shop.domain.model.helpcenter.HelpCenterDetailList
import com.alexis.shop.domain.model.helpcenter.HelpCenterDetailModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterItemModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterModel
import com.alexis.shop.domain.repository.helpcenter.IHelpCenterRepository
import com.alexis.shop.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelpCenterRepository @Inject constructor(
    private val helpCenterDataSource : HelpCenterRemoteDataSource
) : IHelpCenterRepository{
    override fun getHelpCenter(): Flow<Resource<HelpCenterModel>> {
        return flow<Resource<HelpCenterModel>> {
            emit(Resource.Loading())
            when(val apiResponse = helpCenterDataSource.getHelpCenter().first()) {
                is ApiResponse.Success -> emit(
                   Resource.Success( generateHelpCenterModel(apiResponse.data.data.items))
                )
                is ApiResponse.Empty -> listOf<HelpCenterModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getFAQHelp(): Flow<Resource<List<HelpCenterDetailModel>>> {
        return flow<Resource<List<HelpCenterDetailModel>>> {
            emit(Resource.Loading())
            when(val apiResponse = helpCenterDataSource.getHelpCenter().first()){
                is ApiResponse.Success -> emit(
                    Resource.Success(generateHelpCenterDetail(
                       apiResponse.data.data.items.map { it.helpCenterDetail }[0] )
                    )
                )
                is ApiResponse.Empty -> listOf<HelpCenterDetailModel>()
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }
    // as ArrayList<HelpCenterDetail>

    private fun generateHelpCenterModel(data : List<HelpCenterItem?>?) : HelpCenterModel {
        return if (!data.isNullOrEmpty()) {
            HelpCenterModel(
                helpCenter = data.map {
                    HelpCenterItemModel(
                        helpCenterDetail = generateHelpCenterDetail(it?.helpCenterDetail),
                        id = it?.id.orZero(),
                        name = it?.name.orEmpty()
                    )
                }
            )
        }else{
            HelpCenterModel()
        }
    }

    private fun generateHelpCenterDetail(data : HelpCenterDetailItem?) : List<HelpCenterDetailModel> {

       return data?.helpCenterDetail?.map {
           HelpCenterDetailModel(
               answer = it.answer,
               helpCenterId = it.helpCenterId,
               id = it.id,
               question = it.question,
               choosed = it.choosed
           )
       } ?: listOf()
    }
}