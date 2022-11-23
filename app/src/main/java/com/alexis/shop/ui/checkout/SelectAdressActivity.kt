package com.alexis.shop.ui.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.databinding.ActivitySelectAdressBinding
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.ui.shopping_bag.adapter.SelectAddressAdapter
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectAdressActivity : AppCompatActivity() {

    private val viewModel : SelectAddressFragmentViewModel by viewModels()
    private lateinit var binding : ActivitySelectAdressBinding
    private var selectedDeliver = ""
    private var checkoutAddress = ArrayList<AddressItemModel>()
    lateinit var adapterAddress : SelectAddressAdapter
    lateinit var sharedPref : SheredPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleview()
    }

    private fun initRecycleview() {
        with(binding.recycleAddress) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterAddress
        }
    }

    private fun setListener(){
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}