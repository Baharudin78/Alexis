package com.alexis.shop.ui.shopping_bag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivityShopingBagBinding
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.ui.checkout.SelectAdressActivity
import com.alexis.shop.ui.shopping_bag.adapter.ShoppingBagAdapter
import com.alexis.shop.ui.wishlist.WishlistViewModel
import com.alexis.shop.utils.*
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.activity_shoping_bag.*
import kotlinx.android.synthetic.main.fragment_shopping_bag.*
import kotlinx.android.synthetic.main.fragment_shopping_bag.blurView


@AndroidEntryPoint
class ShopingBagActivity : AppCompatActivity(), OnShoppingBagClickItem {

    private lateinit var binding : ActivityShopingBagBinding
    private val viewModel: ShoppingBagViewModel by viewModels()
    private val wishListViewModel : WishlistViewModel by viewModels()
    lateinit var adapterBill: ShoppingBagAdapter
    private var shoppingBagList = ArrayList<ShoppingBagModel>()
    private var totalPrice = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopingBagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resetAnimation()
        setupRecycleview()
        blurView()
        getShopingBag()
        setListener()
        validateButton()
        //getPrice()
    }

    private fun resetAnimation() {
        binding.gilding.resetLottieAnimation()
    }

    private fun setupRecycleview() {
        adapterBill = ShoppingBagAdapter(binding.root.context, this)
        with(binding.recycleShoppingbag) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterBill
        }
    }

    private fun postWishlist(itemCode : String) {
                viewModel.postWishlist(itemCode).observe(this) { response ->
                    if (response != null) {
                        when (response) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                Toast.makeText(applicationContext, "Ditambakan ke Wishlist", Toast.LENGTH_SHORT).show()
                            }
                            is Resource.Error -> {
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.auth_error, "Post Wishlist"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
    }
    private fun getShopingBag() {
        viewModel.getShoppingCart().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val shoppingValue = response.data?.bag as ArrayList<ShoppingBagModel>
                        shoppingBagList = shoppingValue
                        adapterBill.setData(shoppingValue)
                        val position = shoppingBagList.size
                        for (i in 0 until shoppingBagList.size) {
                            totalPrice += shoppingBagList[i].price
                            binding.tvTotal.text = totalPrice.toString()
                            adapterBill.notifyItemRemoved(position)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            this.applicationContext,
                            getString(R.string.auth_error, "Get Shopping Bag"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun validateButton() {
        val totalHarga = binding.tvTotal.text
        if (totalHarga.toString().equals(null)) {
            binding.submit.visibility = View.GONE
        }
    }

    private fun setListener() {
        with(binding) {
            submit.setOnClickListener {
                val intent = Intent(this@ShopingBagActivity, SelectAdressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun deleteShoppingBag(item : ShoppingBagModel ) {
        viewModel.deleteShoppingBag(item.id).observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> deleteFunc(item)
                    is Resource.Error -> {
                        Toast.makeText(
                            this.applicationContext,
                            getString(R.string.auth_error, "Delete shopping bag"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getPrice() {
        for (i in 0 until shoppingBagList.size) {
            totalPrice += shoppingBagList[i].price
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StatusBarUtil.forceStatusBar(window, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtil.forceStatusBar(window, true)
    }

    override fun onResume() {
        super.onResume()
        StatusBarUtil.forceStatusBar(window, true)
    }

    private fun deleteFunc(item: ShoppingBagModel) {
        shoppingBagList.remove(item)
        adapterBill.setData(shoppingBagList)
    }
    private fun blurView() {
        val radius = 15f
        val decorView : View = window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        blurView.setupWith(rootView, RenderScriptBlur(applicationContext))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    override fun onDeleteItem(item: Any) {
        item as ShoppingBagModel
        deleteShoppingBag(item)
    }

    override fun onMove2WishList(item: Any) {
        item as ShoppingBagModel
        postWishlist(item.product_item_code)
    }

    override fun onEditItem(item: Any) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerShoping, EditShoppingBagFragment()).commit()
    }
}