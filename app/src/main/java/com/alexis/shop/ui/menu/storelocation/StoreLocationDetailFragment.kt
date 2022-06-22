package com.alexis.shop.ui.menu.storelocation

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.ui.menu.maps.MapsFragment
import com.alexis.shop.ui.menu.adapter.DetailLocationAdapter
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import com.alexis.shop.utils.menuNavigator
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentStoreLocationDetailBinding
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreLocationDetailFragment : BaseFragment<FragmentStoreLocationDetailBinding>(), OnClickItem {
    private var storeProvinceName: String = ""
    private val viewModel: StoreLocationViewModel by viewModels()
    private lateinit var storeLocationAdapter: DetailLocationAdapter

    override fun getViewBinding() = FragmentStoreLocationDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        if (arguments != null) {
            storeProvinceName = requireArguments().getString(ARG_STORE_PROVINCE).orEmpty()
        }
    }

    override fun main() {
        binding.titleMode.text = storeProvinceName
        storeLocationAdapter = DetailLocationAdapter(this)
        with(binding.recycleStoreloc) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = storeLocationAdapter
        }
        setListener()
        getStoreLocationByName()
    }

    private fun setListener() {
        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnCancel.setOnClickListener {
                justOut()
            }
        }
    }

    private fun getStoreLocationByName() {
        viewModel.getStoreLocationByName(storeProvinceName).observe(viewLifecycleOwner) { response ->
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

    override fun onClick(item: Any) {
        activity?.supportFragmentManager?.menuNavigator(
            MapsFragment.newInstance((item as StoreLocationByNameModel))
        )
    }

    companion object {
        private const val ARG_STORE_PROVINCE = "ARG_STORE_PROVINCE"
        fun newInstance(storeProvince: String): StoreLocationDetailFragment {
            return StoreLocationDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_STORE_PROVINCE, storeProvince)
                }
            }
        }
    }
}