package com.alexis.shop.ui.shopping_bag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivitySelectPromoBinding
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.ui.account.voucher.SimpleVoucherAdapter
import com.alexis.shop.ui.account.voucher.VoucherViewModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.resetLottieAnimation
import com.alexis.shop.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class SelectPromoActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectPromoBinding
    private val viewModel : VoucherViewModel by viewModels()
    private var voucherItem = ArrayList<VoucherItemModel>()
    private lateinit var voucherAdapter : SimpleVoucherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        blurView()
        resetAnimation()
        initRecylecyview()
        getVoucher()
        setListener()


    }
    private fun resetAnimation() {
        binding.gilding.resetLottieAnimation()
    }

    private fun initRecylecyview(){
        voucherAdapter = SimpleVoucherAdapter(this, object : OnClickItem{
            override fun onClick(item: Any) {
                item as VoucherItemModel
                val voucherSelected = item.amount
                binding.tVoucher.text = voucherSelected.toString()
            }
        })
        with(binding.recycleVoucher) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = voucherAdapter
        }
    }

    private fun getVoucher() {
        viewModel.getAllVoucher().observe(this) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading ->{}
                    is Resource.Success -> {
                        val voucherValue = response.data?.data as ArrayList<VoucherItemModel>
                        voucherItem = voucherValue
                        voucherAdapter.setData(voucherValue)
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

    private fun setListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.promo_activity, ReviewOrderSummaryFragment()).commit()
        }

    }
}