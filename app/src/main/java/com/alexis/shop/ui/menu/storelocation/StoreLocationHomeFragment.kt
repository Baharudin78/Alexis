package com.alexis.shop.ui.menu.storelocation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.databinding.FragmentStoreLocationBinding
import com.alexis.shop.domain.model.store_location.AllStoreItemModel
import com.alexis.shop.ui.menu.adapter.SimpleLocationAdapter
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import com.alexis.shop.utils.menuNavigator
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class StoreLocationHomeFragment : BaseFragment<FragmentStoreLocationBinding>(), OnClickItem {
    private val viewModel: StoreLocationViewModel by viewModels()
    private lateinit var storeLocationAdapter: SimpleLocationAdapter
    private var allStoreLocation = ArrayList<AllStoreItemModel>()

    override fun getViewBinding() = FragmentStoreLocationBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    override fun main() {
        blurView()
        storeLocationAdapter = SimpleLocationAdapter(binding.root.context, this)
        with(binding.recycleStore) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = storeLocationAdapter
        }
        getAllStoreLocation()
        setListener()
    }

    private fun getAllStoreLocation() {
        viewModel.getAllStoreLocation().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val allStoreValue = response.data?.data as ArrayList<AllStoreItemModel>
                        allStoreLocation = allStoreValue
                        storeLocationAdapter.setData(allStoreValue)
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

    private fun setListener() {
        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnCancel.setOnClickListener {
                var index = allStoreLocation.size
                for (data in allStoreLocation) {
                    Animations.runAnimation(
                        requireActivity(),
                        Animations.ANIMATION_OUT,
                        index,
                        recycleStore.layoutManager!!.findViewByPosition(index)!!.rootView
                    )

                    if (index != 1) {
                        index -= 1
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    justOut()
                }, 1000L)
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

    override fun onClick(item: Any) {
        requireActivity().supportFragmentManager.menuNavigator(
            StoreLocationDetailFragment.newInstance(item as String)
        )
    }

    companion object {
        fun newInstance(): StoreLocationHomeFragment {
            return StoreLocationHomeFragment()
        }
    }
}