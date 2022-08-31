package com.alexis.shop.ui.detail.adapter.viewholder

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.ui.detail.ExpanItemPagersActivity
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.main.MainActivity
import com.alexis.shop.utils.loadImage
import com.alexis.shop.utils.log
import com.dizcoding.mylibrv.AbstractViewHolder
import kotlinx.android.synthetic.main.item_dashboard_objectitems.view.*

class SubCategoryProductVH(
    itemView: View
): AbstractViewHolder<SubCategoryProduct>(itemView) {
    private val title = itemView.title
    private val price = itemView.harga
    private val image = itemView.gmbBara
    private val lTitleimage = itemView.layoutTitle
    private val downloadView = itemView.downloadView
    private val btnDownload = itemView.btnDownload

    companion object{
        const val LAYOUT = R.layout.item_dashboard_objectitems
    }

    override fun bind(element: SubCategoryProduct) {
        title.text = element.productModels.name
        price.text = element.productModels.price.toString()
        //image.loadImage(element.productModels.product_image?.imageProduct?.map { it.image_url }.toString())
        image.loadImage(element.productModels.product_image?.map { it.image_url }.toString())

//        when (element.productModels.imageType) {
//            "double" -> typeASetting(element)
//            "single" -> typeBSetting(element)
//            "C" -> typeCSetting(element)
//            else -> typeCSetting(element)
//        }

        itemView.setOnClickListener {
            openDetail(itemView, element.productModels)
        }

        itemView.setOnLongClickListener {
            log("longClicked")
            downloadView.isVisible = true
            true
        }

        btnDownload.setOnClickListener {
            log("donwloading..")
        }

        downloadView.setOnClickListener {
            downloadView.isVisible = false
        }
    }

    private fun typeASetting(element: SubCategoryProduct){
        val layoutParamsImage = LinearLayout.LayoutParams(element.layoutWidth/2, element.layoutHeight*40/10)
        val layoutParamsTitle = LinearLayout.LayoutParams(element.layoutWidth/2, element.layoutHeight*15/10)
        image.layoutParams = layoutParamsImage
        lTitleimage.layoutParams = layoutParamsTitle
    }

    fun typeBSetting(element: SubCategoryProduct) {
        val layoutParamsImage = LinearLayout.LayoutParams(element.layoutWidth, element.layoutHeight*80/10)
        val layoutParamsTitle = LinearLayout.LayoutParams(element.layoutWidth, element.layoutHeight*15/10)
        image.layoutParams = layoutParamsImage
        lTitleimage.layoutParams = layoutParamsTitle
    }

    private fun typeCSetting(element: SubCategoryProduct){
        val layoutParamsImage = LinearLayout.LayoutParams(element.layoutWidth, element.layoutHeight*40/10)
        val layoutParamsTitle = LinearLayout.LayoutParams(element.layoutWidth, element.layoutHeight*15/10)
        image.layoutParams = layoutParamsImage
        lTitleimage.layoutParams = layoutParamsTitle
    }

    private fun openDetail(itemView: View, data: ProductBaruModel) {
        val context = itemView.context as MainActivity
        val intent = Intent(context, ExpanItemPagersActivity::class.java).apply {
            putExtra(ExpanItemPagersActivity.EXTRA_DATA, data)
        }

//        if (data.imageType == "C") {
//            context.startActivity(intent)
//        }
       // else {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                itemView,
                "shared_transition"
            )
            //context.resultLauncher.launch(intent, options)
       // }
    }
}