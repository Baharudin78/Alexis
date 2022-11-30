package com.alexis.shop.ui.shopping_bag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivitySelectVoucherBinding
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.ui.account.voucher.SimpleVoucherAdapter
import com.alexis.shop.ui.account.voucher.VoucherViewModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.justOut
import com.alexis.shop.utils.resetLottieAnimation
import com.alexis.shop.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur


@AndroidEntryPoint
class SelectVoucherActivity : AppCompatActivity(), OnClickItem {

    private lateinit var binding : ActivitySelectVoucherBinding
    private val viewModel : VoucherViewModel by viewModels()
    private var allVoucher = ArrayList<VoucherItemModel>()
    private lateinit var voucherAdapter: SimpleVoucherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectVoucherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resetAnimation()
        blurView()
        initRecycleview()
        getAllVoucher()
        setListener()
    }

    private fun resetAnimation() {
        binding.gilding.resetLottieAnimation()
    }
    private fun initRecycleview() {
        voucherAdapter = SimpleVoucherAdapter(this, this)
        with(binding.recycleVoucher){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = voucherAdapter
        }
    }

    private fun getAllVoucher() {
        viewModel.getAllVoucher().observe(this) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val getAllVoucherValue = response.data?.data as ArrayList<VoucherItemModel>
                        allVoucher = getAllVoucherValue
                        voucherAdapter.setData(getAllVoucherValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            "GetVoucherFailed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private fun setListener(){
        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
            btnCancel.setOnClickListener {
                var index = allVoucher.size
                for (data in allVoucher) {
                    Animations.runAnimation(
                        applicationContext,
                        Animations.ANIMATION_OUT,
                        index,
                        recycleVoucher.layoutManager!!.findViewByPosition(index)!!.rootView
                    )
                    if (index != 1) {
                        index -= 1
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, 1000L)
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
        item as VoucherItemModel
        val voucherSelected = item.amount
        binding.tvtotalVoucher.text = voucherSelected.toString()
        toast("${item.amount}")
    }
}