package com.alexis.shop.ui.menu.address

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentChangeAddressBinding
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.ui.account.MyAccountFragment
import com.alexis.shop.ui.checkout.SelectAddressFragmentViewModel
import com.alexis.shop.ui.menu.adapter.ChangeAddressAdapter
import com.alexis.shop.ui.shopping_bag.adapter.SelectAddressAdapter
import com.alexis.shop.utils.*
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_change_address.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ChangeAddressFragment : BaseFragment<FragmentChangeAddressBinding>(), OnAddressClick {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView
    lateinit var add_button: ConstraintLayout
    lateinit var title: TextView
    lateinit var recycle: RecyclerView
    private var checkoutAddress = ArrayList<AddressItemModel>()
    lateinit var adapterIt: SelectAddressAdapter
    private val viewModel: SelectAddressFragmentViewModel by viewModels()
    override fun getViewBinding()= FragmentChangeAddressBinding.inflate(layoutInflater)

    private lateinit var addressAdapter: ChangeAddressAdapter
    private var arrayDate: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        super.main()
        blurView()
        adapterIt = SelectAddressAdapter(binding.root.context, this)
        with(binding) {
            recycleAddress.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = adapterIt
            }
            btnCancel.setOnClickListener {
                justOut()
            }
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            buttonAddAddress.setOnClickListener {
                val intent = Intent(requireContext(), AddAddressActivity::class.java)
                startActivity(intent)
            }
        }
        getAddress()
    }

    private fun deleteAddress(item : AddressItemModel){
        viewModel.deleteAddress(item.id.orZero()).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> deleteFunc(item)
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.auth_error, "Delete  address"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun activeAddress(item : AddressItemModel){
        viewModel.setSetActive(item.id.orZero()).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> deleteFunc(item)
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.auth_error, "Delete  address"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private fun getAddress() {
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
        blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeAddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun deleteFunc(item : AddressItemModel){
        checkoutAddress.remove(item)
        adapterIt.setData(checkoutAddress)
    }
    override fun delete(item: Any) {
        item as AddressItemModel
        deleteAddress(item)
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