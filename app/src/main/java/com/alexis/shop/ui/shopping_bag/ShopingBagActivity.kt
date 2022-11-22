package com.alexis.shop.ui.shopping_bag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.databinding.ActivityShopingBagBinding
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.ui.shopping_bag.adapter.ShoppingBagAdapter
import com.alexis.shop.utils.OnShoppingBagClickItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopingBagActivity : AppCompatActivity(), OnShoppingBagClickItem {

    private lateinit var binding : ActivityShopingBagBinding
    private val viewModel: ShoppingBagViewModel by viewModels()
    lateinit var adapterBill: ShoppingBagAdapter
    private var shoppingBagList = ArrayList<ShoppingBagModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopingBagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleview()
    }

    private fun setupRecycleview() {
        adapterBill = ShoppingBagAdapter(binding.root.context, this)
        with(binding.recycleShoppingbag) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterBill
        }
    }

    override fun onDeleteItem(item: Any) {
        TODO("Not yet implemented")
    }

    override fun onMove2WishList(item: Any) {
        TODO("Not yet implemented")
    }

    override fun onEditItem(item: Any) {
        TODO("Not yet implemented")
    }
}