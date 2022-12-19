package com.alexis.shop.ui.menu.storelocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivityStoreLocationDetailBinding
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.ui.menu.adapter.DetailLocationAdapter
import com.alexis.shop.utils.OnClickItem
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class StoreLocationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStoreLocationDetailBinding
    private var storeProvinceName = ""
    private val viewModel : StoreLocationViewModel by viewModels()
    private lateinit var storeLocationAdapter : DetailLocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val province = intent.getStringExtra(STORE_NAME)
        binding.titleMode.text = province.orEmpty()
        setupAdapter()
        setListener()
        getStoreLocationByName(province.orEmpty())
        blurView()
    }

    private fun setupAdapter(){
        storeLocationAdapter = DetailLocationAdapter(object : OnClickItem{
            override fun onClick(item: Any) {
                item as StoreLocationByNameModel

            }
        })
        with(binding.recycleStoreloc){
            layoutManager = LinearLayoutManager(this@StoreLocationDetailActivity)
            adapter = storeLocationAdapter
        }
    }

    private fun setListener(){
        with(binding){
            btnBack.setOnClickListener {
                finish()
            }
            btnCancel.setOnClickListener {
                finish()
            }
        }
    }

    private fun getStoreLocationByName(province : String) {
        viewModel.getStoreLocationByName(province).observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        storeLocationAdapter.setData(response.data as ArrayList<StoreLocationByNameModel>)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            getString(R.string.auth_error, "Get Store Location"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun blurView() {
        val radius = 15f
        val decorView : View = window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(this))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    companion object{
        const val STORE_NAME = "STORE_NAME"
    }

}