package com.alexis.shop.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivitySelectAdressBinding
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.ui.menu.address.AddAddressActivity
import com.alexis.shop.ui.menu.address.AddAddressFragment
import com.alexis.shop.ui.shopping_bag.SelectVoucherActivity
import com.alexis.shop.ui.shopping_bag.adapter.SelectAddressAdapter
import com.alexis.shop.utils.*
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur


@AndroidEntryPoint
class SelectAdressActivity : AppCompatActivity() , OnClickItem{

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

        resetAnimation()
        blurView()
        initRecycleview()
        getCheckoutAddress()
        setListener()
    }

    private fun resetAnimation() {
        binding.gilding.resetLottieAnimation()
    }

    private fun initRecycleview() {
        adapterAddress = SelectAddressAdapter(binding.root.context, this)
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
            addMode.setOnClickListener {
                val intent = Intent(this@SelectAdressActivity, AddAddressActivity::class.java)
                startActivity(intent)
            }

            btnSubmit.setOnClickListener {
                val intent = Intent(this@SelectAdressActivity, SelectVoucherActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getCheckoutAddress() {
        viewModel.getCheckoutAddress().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        log("berhasil")
                        val addressValue = response.data?.address as ArrayList<AddressItemModel>
                        log("berhasil $addressValue")
                        checkoutAddress = addressValue
                        adapterAddress.setData(addressValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Get Checkout Address Failed",
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
        binding.blurView.setupWith(rootView, RenderScriptBlur(applicationContext))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    override fun onClick(item: Any) {
        item as AddressItemModel
        toast("Clicked ${item.address}")
    }
}