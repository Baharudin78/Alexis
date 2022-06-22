package com.alexis.shop.ui.checkout

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentSelectAddressBinding
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.ui.menu.address.AddAddressFragment
import com.alexis.shop.ui.menu.address.AddAddressFragment.Companion.SELECT_ADDRESS
import com.alexis.shop.ui.shopping_bag.SelectVoucherFragment
import com.alexis.shop.ui.shopping_bag.adapter.SelectAddressAdapter
import com.alexis.shop.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectAddressFragment : BaseFragment<FragmentSelectAddressBinding>() {
    private val viewModel: SelectAddressFragmentViewModel by viewModels()
    private var selectedDelivery: String = ""
    private var checkoutAddress: ArrayList<CheckoutAddressModelView> = ArrayList()
    lateinit var adapterIt: SelectAddressAdapter

    override fun getViewBinding() = FragmentSelectAddressBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
//        enterTransition = MaterialFadeThrough()
//        exitTransition = MaterialFadeThrough()
    }

    override fun main() {
        with(binding) {
            deliveryType1.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.rounder_white_transparent)
            selectedDelivery = "regular"
            gilding.resetLottieAnimation()
            btnCancel.setOnClickListener {
                justOut()
            }
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            addMode.setOnClickListener {
                val fragment = AddAddressFragment.newInstance(SELECT_ADDRESS, "")
                requireActivity().supportFragmentManager.shopNavigator(fragment)
            }
            btnSubmit.setOnClickListener {
                val fragment = SelectVoucherFragment.newInstance("", "")
                requireActivity().supportFragmentManager.shopNavigator(fragment)
            }
        }
        initRecyclerView()
        getCheckoutAddress()
    }

    private fun initRecyclerView() {
        adapterIt = SelectAddressAdapter(object : OnClickItem {
            override fun onClick(item: Any) {
                item as CheckoutAddressModelView
//                changeChoosen(item)
//                if(item.getData().equals("Add_Location",true)){
//                    val fragment = AddAddressFragment.newInstance(SELECT_ADDRESS,"")
//                    requireActivity().supportFragmentManager.shopNavigator(fragment)
//                }else{
//
//                }
            }
        })

        binding.recycleAddress.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterIt
        }
    }

    private fun getCheckoutAddress() {
        viewModel.getCheckoutAddress().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            checkoutAddress.add(CheckoutAddressModelView(checkoutAddressId = -1))
                            checkoutAddress.addAll(it as ArrayList<CheckoutAddressModelView>)
                            adapterIt.setData(checkoutAddress)
                        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recycleAddress.adapter = null
    }

    fun changeChoosen(item: CheckoutAddressModelView) {
//        val ccc = ArrayList<SelectAddressModel>()
//        for (a in checkoutAddress) {
//            a.setChoosed(a == item)
//            ccc.add(a)
//        }
//        checkoutAddress.clear()
//        checkoutAddress.addAll(ccc)
        adapterIt.notifyDataSetChanged()
    }
}