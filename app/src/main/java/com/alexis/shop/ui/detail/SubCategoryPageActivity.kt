package com.alexis.shop.ui.detail

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.domain.model.product.Product
import com.alexis.shop.data.source.dummy.getListProduct
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.shopping_bag.ShoppingBagFragment
import com.alexis.shop.ui.wishlist.WishlistFragment
import com.alexis.shop.utils.*
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTitle
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTypeAProduct
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.dizcoding.mylibrv.BaseListAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_subcategory_page.*

@AndroidEntryPoint
class SubCategoryPageActivity : AppCompatActivity() {

    val adapter: BaseListAdapter = BaseListAdapter(ItemTypeFactoryImpl())
    var category = ""
    private lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory_page)
        customTopBarsColor(true)

        recycler_products.layoutManager = LinearLayoutManager(this)
        recycler_products.adapter = adapter

        products = getListProduct()
        category = intent.getStringExtra("category").toString()

        option.setOnClickListener {
            supportFragmentManager.commit {
                add<MenuFragment>(R.id.transparent_menu)
                addToBackStack("menu_fragments")
            }
        }

        cart.setOnClickListener {
            supportFragmentManager.commit {
                add<ShoppingBagFragment>(R.id.transparent_menu)
                addToBackStack("shopping_bag_fragments")
            }
        }

        loved.setOnClickListener {
            val fragment = WishlistFragment.newInstance(WishlistModel(), "")
            supportFragmentManager.commit {
                add(R.id.transparent_menu, fragment)
                addToBackStack("wishlist_fragments")
            }
        }

        btn_back.setOnClickListener {
            finish()
        }

        logo_alexis.setOnClickListener {
            finish()
//            customTopBarsColor(true)
        }

        //Set the activity Content
        addTitle()
        if(category.equals("Bags",true)){
            out_of_stock.visible()
        }else {
            addProductsA()
            addProductsC()
            addProductsA2()
            addProductsB()
        }
    }

    private fun addTitle() {
        val subCategoryTitle = SubCategoryTitle(category,
                getOneXMeters(applicationContext), true)
        adapter.addItem(subCategoryTitle)
    }

    private fun addProductsA() {
//        val subCategoryProduct = SubCategoryTypeAProduct(
//            products,
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }
    private fun addProductsA2() {
//        val subCategoryProduct = SubCategoryTypeAProduct(
//            products,
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }

    private fun addProductsB() {
//        val subCategoryProduct = SubCategoryProduct(
//            "Sepatu PU",
//            products[3].price,
//            ImageModel(
//                "http://api.myalexis.xyz:3001/uploads/product_image/234234BE-AYAUB-B1-S1.jpg",
//                false
//            ),
//            "B",
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }

    private fun addProductsC(){
//        val subCategoryProduct = SubCategoryProduct(
//            "Shirt",
//            products[3].price,
//            ImageModel(
//                "http://api.myalexis.xyz:3001/uploads/product_image/234234BF-AYAUB-B1-S1.jpg",
//                false
//            ),
//            "B",
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }

//    override fun onResume() {
//        StatusBarUtil.forceStatusBar(window, false)
//        super.onResume()
//        log("expan onResume")
//    }
//
//    override fun onDestroy() {
//        StatusBarUtil.forceStatusBar(window, true)
//        super.onDestroy()
//        log("expan onDestroy")
//    }
//
//    override fun onStop() {
//        StatusBarUtil.forceStatusBar(window, true)
//        super.onStop()
//        log("expan onStop")
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}