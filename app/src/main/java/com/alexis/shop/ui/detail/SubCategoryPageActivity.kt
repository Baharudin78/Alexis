package com.alexis.shop.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.domain.model.product.Product
import com.alexis.shop.data.source.dummy.getListProduct
import com.alexis.shop.databinding.ActivitySubcategoryPageBinding
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.detail.adapter.SubProductAdapter
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.shopping_bag.ShoppingBagFragment
import com.alexis.shop.ui.wishlist.WishlistFragment
import com.alexis.shop.utils.*
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTitle
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTypeAProduct
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.alexis.shop.ui.main.MainViewModel
import com.dizcoding.mylibrv.BaseListAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_subcategory_page.*
import kotlinx.coroutines.flow.observeOn

@AndroidEntryPoint
class SubCategoryPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySubcategoryPageBinding
    private val viewModel : MainViewModel by viewModels()
    private var product = ArrayList<ProductBaruModel>()
    private var productData: ProductBaruModel? = null
    private lateinit var subProductAdapter : SubProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubcategoryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customTopBarsColor(true)


        val idProduct = intent.getIntExtra(CATEGORY_ID, 0)
        val nameCategory = intent.getStringExtra(CATEGORY_NAME)
        binding.title.text = nameCategory

        Log.d("CATEGIRIEESS", "$idProduct")
        Log.d("CATEGIRIEESS", nameCategory.orEmpty())
        initRecycleview()
        getSubProduct(idProduct)

//        recycler_products.layoutManager = LinearLayoutManager(this)
//        recycler_products.adapter = adapter
//
//        products = getListProduct()
//        category = intent.getStringExtra("category").toString()

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
   //     addTitle()
//        if(category.equals("Bags",true)){
//            out_of_stock.visible()
//        }else {
//            addProductsA()
//            addProductsC()
//            addProductsA2()
//            addProductsB()
//        }
    }

//    private fun addTitle() {
//        val subCategoryTitle = SubCategoryTitle(category,
//                getOneXMeters(applicationContext), true)
//        adapter.addItem(subCategoryTitle)
//    }

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

    private fun initRecycleview(){
        subProductAdapter = SubProductAdapter(this, object : OnClickItem{
            override fun onClick(item: Any) {
                TODO("Not yet implemented")
            }
        })
        with(binding.recyclerProducts){
            layoutManager = GridLayoutManager(context,2)
            adapter = subProductAdapter
        }
    }

    private fun getSubProduct(id : Int){
        viewModel.getCategoryById(id).observe(this){response ->
            if (response != null){
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val getProductValue = response.data?.data as ArrayList<ProductBaruModel>
                        product = getProductValue
                        subProductAdapter.setData(getProductValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            this, "" +
                                    "${response.message},"
                            , Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    companion object{
        const val CATEGORY_ID = "CATEGORY_ID"
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}