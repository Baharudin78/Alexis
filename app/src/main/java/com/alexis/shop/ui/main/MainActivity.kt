package com.alexis.shop.ui.main

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.data.source.dummy.getListProduct
import com.alexis.shop.domain.model.product.Product
import com.alexis.shop.domain.model.product.category.ProductCategoryModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.detail.ExpanItemPagersActivity
import com.alexis.shop.ui.detail.adapter.entity.LandingPage
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTitle
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTypeAProduct
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.shopping_bag.ShoppingBagFragment
import com.alexis.shop.ui.wishlist.WishlistFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.StatusBarUtil.forceStatusBar
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.common.withDelayTime
import com.dizcoding.mylibrv.BaseListAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var booleanColor = false
    private val double = "double"
    lateinit var option: CheckBox
    private var product: ProductCategoryModel? = null
    private lateinit var cart: ImageView
    private lateinit var count_cart: TextView
    private lateinit var loved: ImageView
    private lateinit var count_loved: TextView
    private lateinit var logo: ImageView
   // private lateinit var products: ArrayList<Product>
    private var countCart = 0
    private var countLoved = 0
    private val adapter = BaseListAdapter(ItemTypeFactoryImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        forceStatusBar(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        getProductCategory()
        getSubProductCategory()

      //  products = getListProduct()

        option = findViewById(R.id.option)
        cart = findViewById(R.id.cart)
        count_cart = findViewById(R.id.count_cart)
        loved = findViewById(R.id.loved)
        count_loved = findViewById(R.id.count_loved)
        logo = findViewById(R.id.logo_alexis)

        changeTopBarsColor(false)
        count_loved.text = getSavedPrefsWish().toString()
        count_cart.text = getSavedPrefsCount().toString()

        //Animation bounce
        val ltr = AnimationUtils.loadAnimation(this, R.anim.bounce_in_left)
        val rtl = AnimationUtils.loadAnimation(this, R.anim.bounce_in_right)
        val rtc = AnimationUtils.loadAnimation(this, R.anim.bounce_in_right)

        //Implement Animation bounce on menu option, cart, and wish list
        option.startAnimation(ltr)
        cart.invisible()
        count_cart.invisible()
        loved.invisible()
        count_loved.invisible()

        withDelayTime(800) {
            cart.visible()
            count_cart.visible()

            cart.startAnimation(rtl)
            count_cart.startAnimation(rtl)
        }

        withDelayTime(1600) {
            loved.visible()
            count_loved.visible()

            loved.startAnimation(rtc)
            count_loved.startAnimation(rtc)
        }

        hideBlur()

        val linearLayoutManager = LinearLayoutManager(this)
        base_recycler.layoutManager = linearLayoutManager
        base_recycler.adapter = adapter
        
        //Add Content
        getAllProduct()
        addLandingPage()
        addTitle()
//        addProductsA(adapter)
//        addProductsC(adapter)
//        addProductsA2(adapter)
//        addProductsB(adapter)
//
//        addProductsA(adapter)
//        addProductsC(adapter)
//        addProductsA2(adapter)
//        addProductsB(adapter)

        nested_base.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            if (scrollY >= getTrullyHeightResolution(this) + 5) {
                changeTopBarsColor(true)
            } else {
                changeTopBarsColor(false)
            }
        }
        option.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) withDelay(300) {
                supportFragmentManager.commit {
                    add<MenuFragment>(R.id.transparent_menu)
                    addToBackStack("menu_fragments")
                }
            }
        }

        loved.setPushClickListener {
            val fragment = WishlistFragment.newInstance(WishlistModel(), "")
            supportFragmentManager.commit {
                add(R.id.transparent_menu, fragment)
                addToBackStack("wishlist_fragments")
            }

        }

        cart.setPushClickListener {
            supportFragmentManager.commit {
                add<ShoppingBagFragment>(R.id.transparent_menu)
                addToBackStack("shopping_bag_fragments")
            }
        }

        logo.setOnClickListener {
            base_recycler.scrollToPosition(1)
            log("clicked")
        }

        getUserData()
    }

//    val resultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                when (result.data?.getIntExtra("id", 0)) {
//                    ExpanItemPagersActivity.BACK_TO_CART -> {
////                        countCart += 1
////                        log("cart")
////                        savedCountToPrefs(countCart)
//                        getUserData()
//                    }
//                    ExpanItemPagersActivity.BACK_TO_WISHLIST -> {
////                        countLoved += 1
////                        savedWishToPrefs(countLoved)
//                        getUserData()
//                    }
//                }
//
////                count_cart.text = getSavedPrefsCount().toString()
////                count_loved.text = getSavedPrefsWish().toString()
//            }
//        }

    private fun changeTopBarsColor(isBlack: Boolean) {
        //Make top bar (signal, baterai) and menu bar transparent
        val color: Int = when (isBlack) {
            true -> R.color.black
            false -> R.color.white
        }

        val checkBoxColor: Int = when (isBlack) {
            true -> Color.BLACK
            false -> Color.WHITE
        }
        a_tx.setColorFilter(ContextCompat.getColor(applicationContext, color))
        logo_alexis.setColorFilter(ContextCompat.getColor(applicationContext, color))
        option.buttonTintList = ColorStateList.valueOf(checkBoxColor)
        cart.setColorFilter(ContextCompat.getColor(applicationContext, color))
        count_cart.setTextColor(ContextCompat.getColor(applicationContext, color))
        loved.setColorFilter(ContextCompat.getColor(applicationContext, color))
        count_loved.setTextColor(ContextCompat.getColor(applicationContext, color))
        customTopBarsColor(isBlack)
        booleanColor = isBlack
    }

    private fun addLandingPage() {
        val lanpage = LandingPage("Top Choices", getTrullyHeightResolution(this))
        adapter.addItem(lanpage)
    }

    private fun addTitle() {
        val subCategoryTitle = SubCategoryTitle("New In", getOneXMeters(applicationContext))
        adapter.addItem(subCategoryTitle)
    }

    private fun addProductsA(adapter: BaseListAdapter) {
//        val subCategoryProduct = SubCategoryTypeAProduct(
//            products,
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }

    private fun addProductsA2(adapter: BaseListAdapter) {
//        val subCategoryProduct = SubCategoryTypeAProduct(
//            products,
//            getOneXMeters(applicationContext),
//            getWidthResolution(applicationContext)
//        )
//        adapter.addItem(subCategoryProduct)
    }

    private fun addProductsB(adapter: BaseListAdapter) {
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
//
//        adapter.addItem(subCategoryProduct)
    }

    private fun addProductsC(adapter: BaseListAdapter) {
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

    override fun onResume() {
        super.onResume()
        forceStatusBar(window, false)
        changeTopBarsColor(booleanColor)
        if (blur_first.isVisible) {
            hideBlur()
        }
    }

    override fun onStart() {
        super.onStart()
        forceStatusBar(window, false)
    }

    private fun hideBlur() {
        blur_first.visible()

        withDelayTime(1500) {
            blur_first.invisible()
        }
    }

    private fun getAllProduct() {
        viewModel.getAllProduct().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            addProductToAdapter(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            applicationContext,
                             getString(R.string.auth_error, "Get Product"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getUserData() {
        if(viewModel.isUserAuthenticated()) {
            getWishlist()
            getShoppingBag()
        }
    }

    private fun getWishlist() {
        viewModel.getWishlist().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            savedWishToPrefs(it.size)
                            count_loved.text = it.size.toString()
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.auth_error, "Get Wishlist"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getShoppingBag() {
        viewModel.getShoppingBag().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            savedCountToPrefs(it.size)
                            count_cart.text = it.size.toString()
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.auth_error, "Get Shopping Bag"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun addProductToAdapter(product: List<ProductBaruModel>) {
        val listDouble = ArrayList<ProductBaruModel>()
        product.forEach {
//            if(it.imageType.lowercase() == double) {
//                listDouble.add(it)
//            } else {
                if(listDouble.isNotEmpty()) {
                    val subCategoryTypeAProduct = SubCategoryTypeAProduct(
                        listDouble,
                        getOneXMeters(applicationContext),
                        getWidthResolution(applicationContext)
                    )
                    adapter.addItem(subCategoryTypeAProduct)
                    listDouble.clear()
                }
                val subCategoryProduct = SubCategoryProduct(
                    it,
                    getOneXMeters(applicationContext),
                    getWidthResolution(applicationContext)
                )
                adapter.addItem(subCategoryProduct)
           // }
        }
    }

    private fun getProductCategory() {
       // viewModel.callProductCategoryData()
    }
    private fun getSubProductCategory() {
       // viewModel.callSubCategoryData(product?.category.orEmpty())
    }
}