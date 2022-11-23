package com.alexis.shop.ui.main

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import com.alexis.shop.data.remote.response.landing.LandingItem
import com.alexis.shop.databinding.ActivityMainBinding
import com.alexis.shop.domain.model.landing.LandingModelItem
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryProduct
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTitle
import com.alexis.shop.ui.detail.adapter.entity.SubCategoryTypeAProduct
import com.alexis.shop.ui.detail.adapter.factory.ItemTypeFactoryImpl
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.menu.storelocation.StoreLocationViewModel
import com.alexis.shop.ui.shopping_bag.ShopingBagActivity
import com.alexis.shop.ui.shopping_bag.ShoppingBagFragment
import com.alexis.shop.ui.wishlist.WishlistFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.StatusBarUtil.forceStatusBar
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.common.withDelayTime
import com.alexis.shop.utils.prefs.SheredPreference
import com.dizcoding.mylibrv.BaseListAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val viewModelLoc : StoreLocationViewModel by viewModels()
    private var booleanColor = false
    private val double = "double"
//    lateinit var option: CheckBox
    lateinit var sharedPref : SheredPreference
    private lateinit var binding : ActivityMainBinding
//    private var product: ProductCategoryNewModel? = null
//    private var imageLanding : LandingModelItem? = null
//    private lateinit var cart: ImageView
//    private lateinit var count_cart: TextView
//    private lateinit var loved: ImageView
//    private lateinit var count_loved: TextView
//    private lateinit var logo: ImageView
   // private lateinit var products: ArrayList<Product>
//    private var countCart = 0
//    private var countLoved = 0
    private val adapters = BaseListAdapter(ItemTypeFactoryImpl())
 //   private lateinit var storeHomeAdapter : StoreHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        forceStatusBar(window, false)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        sharedPref = SheredPreference(this)
        //    storeHomeAdapter = StoreHomeAdapter(binding.root.context)

      //  products = getListProduct()
//        with(binding.locRecycler) {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = storeHomeAdapter
//        }
//        option = findViewById(R.id.option)
//        cart = findViewById(R.id.cart)
//        count_cart = findViewById(R.id.count_cart)
//        loved = findViewById(R.id.loved)
//        count_loved = findViewById(R.id.count_loved)
//        logo = findViewById(R.id.logo_alexis)

        changeTopBarsColor(false)
        binding.countLoved.text = getSavedPrefsWish().toString()
        binding.countCart.text = getSavedPrefsCount().toString()

        //Animation bounce
        val ltr = AnimationUtils.loadAnimation(this, R.anim.bounce_in_left)
        val rtl = AnimationUtils.loadAnimation(this, R.anim.bounce_in_right)
        val rtc = AnimationUtils.loadAnimation(this, R.anim.bounce_in_right)

        //Implement Animation bounce on menu option, cart, and wish list
        binding.option.startAnimation(ltr)
        binding.cart.invisible()
        binding.countCart.invisible()
        binding.loved.invisible()
        binding.countLoved.invisible()

        withDelayTime(800) {
            binding.cart.visible()
            binding.countCart.visible()

            binding.cart.startAnimation(rtl)
            binding.countCart.startAnimation(rtl)
        }

        withDelayTime(1600) {
            binding.loved.visible()
            binding.countLoved.visible()

            binding.loved.startAnimation(rtc)
            binding.countLoved.startAnimation(rtc)
        }

        hideBlur()

        val linearLayoutManager = LinearLayoutManager(this)
        binding.baseRecycler.layoutManager = linearLayoutManager
        binding.baseRecycler.adapter = adapters
        
        //Add Content
        getLandingImage()
        //addTitle()
        getAllProduct()
        getStore()
       // addLandingPage()
//        addProductsA(adapter)
//        addProductsC(adapter)
//        addProductsA2(adapter)
//        addProductsB(adapter)
//
//        addProductsA(adapter)
//        addProductsC(adapter)
//        addProductsA2(adapter)
//        addProductsB(adapter)

        binding.nestedBase.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            if (scrollY >= getTrullyHeightResolution(this) + 5) {
                changeTopBarsColor(true)
            } else {
                changeTopBarsColor(false)
            }
        }
        binding.option.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) withDelay(300) {
                supportFragmentManager.commit {
                    add<MenuFragment>(R.id.transparent_menu)
                    addToBackStack("menu_fragments")
                }
            }
        }

        binding.loved.setPushClickListener {
            val fragment = WishlistFragment.newInstance(WishlistModel(), "")
            supportFragmentManager.commit {
                add(R.id.transparent_menu, fragment)
                addToBackStack("wishlist_fragments")
            }

        }

        binding.cart.setOnClickListener{
            startActivity(Intent(this, ShopingBagActivity::class.java))
        }
//        binding.cart.setPushClickListener {
//            supportFragmentManager.commit {
//                add<ShoppingBagFragment>(R.id.transparent_menu)
//                addToBackStack("shopping_bag_fragments")
//            }
//        }

        binding.logoAlexis.setOnClickListener {
            binding.baseRecycler.scrollToPosition(1)
            log("clicked")
        }

        getUserData()
    }

//    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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
        binding.aTx.setColorFilter(ContextCompat.getColor(applicationContext, color))
        binding.logoAlexis.setColorFilter(ContextCompat.getColor(applicationContext, color))
        binding.option.buttonTintList = ColorStateList.valueOf(checkBoxColor)
        binding.cart.setColorFilter(ContextCompat.getColor(applicationContext, color))
        binding.countCart.setTextColor(ContextCompat.getColor(applicationContext, color))
        binding.loved.setColorFilter(ContextCompat.getColor(applicationContext, color))
        binding.countLoved.setTextColor(ContextCompat.getColor(applicationContext, color))
        customTopBarsColor(isBlack)
        booleanColor = isBlack
    }

    override fun onResume() {
        super.onResume()
        forceStatusBar(window, false)
        changeTopBarsColor(booleanColor)
        if (binding.blurView.isVisible) {
            hideBlur()
        }
    }

    override fun onStart() {
        super.onStart()
        forceStatusBar(window, false)
    }

    private fun hideBlur() {
        binding.blurView.visible()

        withDelayTime(1000) {
            binding.blurView.invisible()
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
                            it.map {
                                Log.d("asdjasldhlj", "${it.id}")
                                Log.d("asdjasldhlj", "${it.name}")
                                Log.d("asdjasldhlj", "${it.price}")
                                Log.d("asdjasldhlj", "${it.barcode}")
                                Log.d("asdjasldhlj", "${it.item_code}")
                            }
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

    private fun getLandingImage() {
        viewModel.getLandingImage().observe(this) {response ->
            if (response != null){
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            Log.d("LANDNUG", "$it")
                            val lanpage = LandingItem(it.mobileLandingImage, layoutHeight =  getTrullyHeightResolution(this))
                            adapters.addItem(lanpage)
                        }
                    }
                    is Resource.Error -> {
                       Toast.makeText(
                           applicationContext,
                           "Error get Imgae",
                           Toast.LENGTH_SHORT)
                           .show()
                    }
                }
            }
        }
    }
    private fun addTitle() {
        val subCategoryTitle = SubCategoryTitle("New Indonesia", getOneXMeters(applicationContext))
        adapters.addItem(subCategoryTitle)
    }

    private fun getStore() {
        viewModelLoc.getStoreHome().observe(this) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                         //   val storeValue = response.data
//                            val store = StoreLocationType(it)
//                            adapters.addItem(store)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            applicationContext,
                            "Get Loc fail",
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
                            binding.countLoved.text = it.size.toString()
                        }
                    }
                    is Resource.Error -> {
//                        val intent = Intent(this, LoginFragment::class.java)
//                        startActivity(intent)
//                        finish()
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
                          //  savedCountToPrefs()
                          //  count_cart.text = it.size.toString()
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
        Log.d("locationnnn", "MASUKK")
        product.forEach {
//            if(it.imageType.lowercase() == double) {
//                listDouble.add(it)
//            } else {
            Log.d("locationnnn", "${it.name}")
            if(listDouble.isNotEmpty()) {
                Log.d("produck2", "${listDouble}")
                val subCategoryTypeAProduct = SubCategoryTypeAProduct(
                        listDouble,
                        getOneXMeters(applicationContext),
                        getWidthResolution(applicationContext)
                    )
                Log.d("produck3", "${it.name}")
                adapters.addItem(subCategoryTypeAProduct)
                    listDouble.clear()
                }
                val subCategoryProduct = SubCategoryProduct(
                    it,
                    getOneXMeters(applicationContext),
                    getWidthResolution(applicationContext)
                )
            Log.d("produck4", "$subCategoryProduct")
            adapters.addItem(subCategoryProduct)
           // }
        }
    }

//    private fun addLocationInHome(location : List<AllStoreItemModel> ) {
//        val list = ArrayList<AllStoreItemModel>()
//        Log.d("locationnnn", "MASUKK")
//        try {
//            location.forEach {
//                Log.d("locationnnn1", it.name)
//                if (list.isNotEmpty()) {
//                    Log.d("locationnnn2", it.name)
//                    val locationHomes = StoreLocationType(
//                        list
//                    )
//                    adapters.addItem(locationHomes)
//                }
//            }
//        }catch (e : Exception) {
//            Log.d("locatio", e.localizedMessage.orEmpty())
//        }
//    }

}