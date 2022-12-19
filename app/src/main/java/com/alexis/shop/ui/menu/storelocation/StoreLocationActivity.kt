package com.alexis.shop.ui.menu.storelocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivityStoreLocationBinding
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.ui.menu.adapter.SimpleLocationAdapter
import com.alexis.shop.utils.OnClickItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StoreLocationActivity : AppCompatActivity() {
    private val viewModel : StoreLocationViewModel by viewModels()
    private lateinit var binding : ActivityStoreLocationBinding
    private lateinit var storeAdapter : SimpleLocationAdapter
    private var storeLocation = ArrayList<AllStoreItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecycleview(){
        storeAdapter = SimpleLocationAdapter(this, object : OnClickItem{
            override fun onClick(item: Any) {

            }
        })
        with(binding.recycleStore) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = storeAdapter
        }
    }

    private fun getAllStoreLocation(){
        viewModel.getAllStoreLocation().observe(this){ response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success ->{
                        val allStoreValue = response.data?.data as ArrayList<AllStoreItemModel>
                        storeLocation = allStoreValue
                        storeAdapter.setData(allStoreValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            getString(R.string.auth_error, "Get All Store Location"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}