package com.alexis.shop.ui.detail

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.viewpager2.widget.ViewPager2
import com.alexis.shop.R
import com.alexis.shop.domain.model.product.ProductsModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.detail.adapter.ExpanFragmentPagerAdapter
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.shopping_bag.ShoppingBagFragment
import com.alexis.shop.ui.wishlist.WishlistFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelay
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpanItemPagersActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: ExpanFragmentPagerAdapter
    private lateinit var fragments: ArrayList<Fragment>
   // var productsModel: ProductsModel? = null
    var productsModel: ProductBaruModel? = null
    lateinit var btn_menu: ImageView
    lateinit var btn_cart: ImageView
    lateinit var btn_love: ImageView
    lateinit var btn_back: ImageView
    lateinit var app_logo: ImageView
    lateinit var txt_love: TextView
    lateinit var txt_cart: TextView
    lateinit var a_tx: ImageView

    var onAnimateHandler: (() -> Unit)? = null

    companion object {
        var BACK_TO_MAIN_CODE = 4212
        var BACK_TO_CART = 1
        var BACK_TO_WISHLIST = 2
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).transitionName = "shared_transition"

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 500L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 500L
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expan_item_pagers)
        supportActionBar?.hide()
        transparentNavBar()
        customTopBarsColor(true)
        getIntentData()

        btn_menu = findViewById(R.id.option)
        btn_cart = findViewById(R.id.cart)
        btn_love = findViewById(R.id.loved)
        btn_back = findViewById(R.id.btn_back)
        app_logo = findViewById(R.id.logo_alexis)
        txt_cart = findViewById(R.id.count_cart)
        txt_love = findViewById(R.id.count_loved)
        a_tx = findViewById(R.id.a_tx)
        initPaging()

        txt_love.text = getSavedPrefsWish().toString()
        txt_cart.text = getSavedPrefsCount().toString()

        btn_menu.setOnClickListener {
            supportFragmentManager.commit {
                add<MenuFragment>(R.id.transparent_menu)
                addToBackStack("menu_fragments")
            }
        }

        btn_cart.setPushClickListener {
            supportFragmentManager.commit {
                add<ShoppingBagFragment>(R.id.transparent_menu)
                addToBackStack("shopping_bag_fragments")
            }
        }

        btn_love.setPushClickListener {
            val fragment = WishlistFragment.newInstance(WishlistModel(), "")
            supportFragmentManager.commit {
                add(R.id.transparent_menu, fragment)
                addToBackStack("wishlist_fragments")
            }
        }

        app_logo.setOnClickListener {
            finish()
            customTopBarsColor(true)
        }

        btn_back.setPushClickListener {
            withDelay(3000) { onBackPressed() }
            onAnimateHandler?.let { it() }
            startAnimation()
        }
    }

    private fun getIntentData() {
        intent.getParcelableExtra<ProductBaruModel>(EXTRA_DATA)?.let {
            productsModel = it
        }
    }

    private fun startAnimation() {
        listOf(btn_back, btn_cart, btn_love, btn_menu, app_logo, txt_cart, txt_love, a_tx).forEach {
            val anim = AnimationUtils.loadAnimation(this, R.anim.animate_quit_fragment)
            withDelay { it.startAnimation(anim) }
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) = Unit
                override fun onAnimationRepeat(animation: Animation?) = Unit
                override fun onAnimationEnd(animation: Animation?) {
                    it.isVisible = false
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        customTopBarsColor(true)
    }

    //Set Page
    private fun initPaging() {
        productsModel?.let {
            fragments = arrayListOf(
                PageFragment.newInstance(it),
//                PageFragment.newInstance(it),
//                PageFragment.newInstance(it),
//                PageFragment.newInstance(it),
//                PageFragment.newInstance(it)
            )

            pagerAdapter = ExpanFragmentPagerAdapter(this, fragments)
            findViewById<ViewPager2>(R.id.pager).apply {
                adapter = pagerAdapter
                reduceDragSensitivity(4)
            }
        }
    }
}