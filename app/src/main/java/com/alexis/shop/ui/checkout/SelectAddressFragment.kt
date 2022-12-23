package com.alexis.shop.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentSelectAddressBinding
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.domain.model.checkout.CheckoutAddressModelView
import com.alexis.shop.ui.menu.address.AddAddressActivity
import com.alexis.shop.ui.menu.address.UpdateAddressActivity
import com.alexis.shop.ui.shopping_bag.SelectVoucherFragment
import com.alexis.shop.ui.shopping_bag.adapter.SelectAddressAdapter
import com.alexis.shop.utils.*
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class SelectAddressFragment : BaseFragment<FragmentSelectAddressBinding>(), OnAddressClick {
    private val viewModel: SelectAddressFragmentViewModel by viewModels()
    private var selectedDelivery: String = ""
    private var checkoutAddress = ArrayList<AddressItemModel>()
    lateinit var adapterIt: SelectAddressAdapter
    lateinit var sharedPref : SheredPreference

    override fun getViewBinding() = FragmentSelectAddressBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(requireContext())
        handleBackPressed()
    }

    override fun main() {
        blurView()
        adapterIt = SelectAddressAdapter(binding.root.context, this)
        with(binding) {
            recycleAddress.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = adapterIt
            }
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
                val intent = Intent(requireContext(), AddAddressActivity::class.java)
                startActivity(intent)
            }
            btnSubmit.setOnClickListener {
                val fragment = SelectVoucherFragment.newInstance("", "")
                requireActivity().supportFragmentManager.shopNavigator(fragment)
            }
        }
       // initRecyclerView()
        getCheckoutAddress()
    }

//    private fun initRecyclerView() {
//        adapterIt = SelectAddressAdapter(binding.root.context,object : OnClickItem {
//            override fun onClick(item: Any) {
//                item as CheckoutAddressModelView
////                changeChoosen(item)
////                if(item.getData().equals("Add_Location",true)){
////                    val fragment = AddAddressFragment.newInstance(SELECT_ADDRESS,"")
////                    requireActivity().supportFragmentManager.shopNavigator(fragment)
////                }else{
////
////                }
//            }
//        })
//
//        binding.recycleAddress.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = adapterIt
//        }
//    }

    private fun getCheckoutAddress() {
        viewModel.getCheckoutAddress().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        log("berhasil")
                        val addressValue = response.data?.address as ArrayList<AddressItemModel>
                        log("berhasil $addressValue")
                        checkoutAddress = addressValue
                        adapterIt.setData(addressValue)
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
        val decorView : View = activity?.window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
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

    override fun delete(item: Any) {
        TODO("Not yet implemented")
    }

    override fun onDropship(item: Any) {
        TODO("Not yet implemented")
    }

    override fun onClick(item: Any) {
        item as AddressItemModel
        Toast.makeText(requireContext(), "Click ${item.address}", Toast.LENGTH_SHORT).show()
    }

    override fun updateItem(item: Any) {
        item as AddressItemModel
        val intent = Intent(requireContext(), AddAddressActivity::class.java)
            .putExtra(UpdateAddressActivity.ADDRESS_DATA, item)
        startActivity(intent)
    }

}