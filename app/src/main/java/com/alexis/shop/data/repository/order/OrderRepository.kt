package com.alexis.shop.data.repository.order

import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.datasource.OrderRemoteDataSource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.data.remote.response.order.*
import com.alexis.shop.data.remote.response.point.CustomerPointItem
import com.alexis.shop.data.remote.response.point.PointItemList
import com.alexis.shop.data.remote.response.point.PointsItem
import com.alexis.shop.data.remote.response.shoppingbag.ShopingProduct
import com.alexis.shop.domain.model.order.*
import com.alexis.shop.domain.model.points.CustomerItemModel
import com.alexis.shop.domain.model.points.PointItemModel
import com.alexis.shop.domain.model.points.PointListModel
import com.alexis.shop.domain.model.shoppingbag.ShopingProductModel
import com.alexis.shop.domain.repository.order.IOrderRepository
import com.alexis.shop.utils.orZero
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrderRepository @Inject constructor(
    private val orderRemoteDataSource: OrderRemoteDataSource
) : IOrderRepository{
    override fun getOrder(): Flow<Resource<OrderListModel>> {
        return flow<Resource<OrderListModel>> {
            emit(Resource.Loading())
            when(val apiResponse = orderRemoteDataSource.getOrder().first()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        generateOrderModel(apiResponse.data.data.items)
                    )
                )
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getPoint(): Flow<Resource<PointListModel>> {
        return flow<Resource<PointListModel>>{
            emit(Resource.Loading())
            when(val apiResponse = orderRemoteDataSource.getPointHitory().first()) {
                is ApiResponse.Success -> emit(Resource.Success(convertPointToModel(apiResponse.data.data.item)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private fun convertPointToModel(data : List<PointsItem?>?) : PointListModel {
        return if (!data.isNullOrEmpty()) {
            PointListModel(
                points = data.map {
                    PointItemModel(
                        createdAt = it?.createdAt.orEmpty(),
                        customer = convertConsumenModel(it?.customer),
                        customer_id = it?.customer_id.orZero(),
                        id = it?.id.orZero(),
                        name = it?.name.orEmpty(),
                        point = it?.point.orZero(),
                        updatedAt = it?.updatedAt.orEmpty()
                    )
                }
            )
        } else{
            PointListModel()
        }
    }

    private fun convertConsumenModel(customer : CustomerPointItem?) : CustomerItemModel {
        return CustomerItemModel(
            createdAt = customer?.createdAt.orEmpty(),
            email = customer?.email.orEmpty(),
            id = customer?.id.orZero(),
            isBlacklist = customer?.is_blacklist.orZero(),
            kodeReferal = customer?.kode_referal.toString(),
            namaLengkap = customer?.nama_lengkap.orEmpty(),
            noTelp = customer?.no_telp.orEmpty(),
            tanggalLahir = customer?.tanggal_lahir.orEmpty(),
            totalPoin = customer?.total_poin.orZero(),
            updatedAt = customer?.updatedAt.orEmpty(),
            userId = customer?.user_id.orZero()
        )
    }

    private fun generateOrderModel(data : List<OrderItem?>?) : OrderListModel {
        return if (!data.isNullOrEmpty()) {
            OrderListModel(
                order = data.map {
                    OrderItemModel(
                        address = generateAddress(it?.address),
                        address_id = it?.address_id.orZero(),
                        customer_id = it?.customer_id.orZero(),
                        detail = generateOrderDetailModel(it?.detail),
                        duration = it?.duration.toString().orEmpty(),
                        final_price = it?.final_price.orZero(),
                        id = it?.id.orZero(),
                        price = it?.price.orZero(),
                        resi_number = it?.resi_number.toString().orEmpty(),
                        shipping_delay = it?.shipping_delay.toString().orEmpty(),
                        status = it?.status.orZero(),
                        transaction_code = it?.transaction_code.orEmpty(),
                        use_voucher = it?.use_voucher.toString().orEmpty()
                    )
                }
            )
        }else{
            OrderListModel()
        }
    }

    private fun generateAddress(alamat : AddressOrderItem?) :AddressItemModel {
        return AddressItemModel(
            address = alamat?.address.orEmpty(),
            address_2 = alamat?.address_2.orEmpty(),
            id = alamat?.id.orZero(),
            as_dropship = alamat?.as_dropship.orZero(),
            customer_id = alamat?.customer_id.orZero(),
            is_default = alamat?.is_default.orZero(),
            latitude = alamat?.latitude.toString().orEmpty(),
            longitude = alamat?.longitude.toString().orEmpty(),
            postal_code = alamat?.postal_code.orZero(),
            recipient_name = alamat?.recipient_name.orEmpty(),
            recipient_phone_number = alamat?.recipient_phone_number.orEmpty(),
            village_id = alamat?.village_id.orZero()
        )
    }

    private fun generateOrderDetailModel(data : List<OrderDetailItem>?) : List<OrderDetailModel> {
        return data?.map {
            OrderDetailModel(
                final_price = it.final_price.orZero(),
                id = it.id.orZero(),
                price = it.price.orZero(),
                product_item_code = it.product_item_code.orEmpty(),
                transaction_order_id = it.transaction_order_id.orZero(),
                product = generateOrderProduct(it.product)
            )
        } ?: mutableListOf()
    }

    private fun generateOrderProduct(data : ProductDetailOrderItem? ) : ProductDetailModel {
        return ProductDetailModel(
            barcode = data?.barcode.orEmpty(),
            color_code = data?.color_code.orEmpty(),
            id = data?.id.orZero(),
            item_code = data?.item_code.orEmpty(),
            name = data?.name.orEmpty(),
            packaging_id = data?.packaging_id.orZero(),
            price = data?.price.orZero(),
            product_material_id = data?.product_material_id.toString().orEmpty(),
            product_size_id = data?.product_size_id.orEmpty(),
            product_subcategory_id = data?.product_subcategory_id.orZero(),
            status = data?.status.orEmpty(),
            stock = data?.stock.orZero(),
            stock_keeping_unit = data?.stock_keeping_unit.toString().orEmpty(),
            store_location_id = data?.store_location_id.orZero(),
            style_code = data?.style_code.orEmpty(),
            user_id = data?.user_id.orZero(),
            weight = data?.weight.orZero()
        )
    }

}