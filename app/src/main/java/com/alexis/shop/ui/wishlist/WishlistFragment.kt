package com.alexis.shop.ui.wishlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentWishlistBinding
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.ui.wishlist.adapter.WishListAdapter
import com.alexis.shop.utils.*
import com.alexis.shop.utils.prefs.SheredPreference
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>(), OnWishlistClickItem {
    private val viewModel: WishlistViewModel by viewModels()
    private var param1: String? = null
    private var param2: String? = null
    lateinit var sharedPref : SheredPreference
    lateinit var adapterBill: WishListAdapter
    private var wishlistData: ArrayList<WishlistModel> = ArrayList()

    override fun getViewBinding() = FragmentWishlistBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(requireContext())
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        enterTransition = MaterialFadeThrough()

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                justOut()
            }
        })
    }

    override fun main() {
        setAdapter()
        blurView()
        binding.btnCancel.setOnClickListener {
            justOut()
        }
        getWishlist()
//        if (param1!! != "") {
//            withDelayTime(300) {
//                deleteFunc(param1!!)
//            }
//        }
    }

    private fun deleteFunc(item: WishlistModel) {
        wishlistData.remove(item)
        adapterBill.setData(wishlistData)
    }

    private fun setAdapter() {
        adapterBill = WishListAdapter()
        adapterBill.setListener(this)
    }

    private fun getWishlist() {
        viewModel.getWishlist().observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            wishlistData = it as ArrayList<WishlistModel>
                            initRecyclerView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(binding.root.context.applicationContext, "Get Wishlist Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView(wishlist: List<WishlistModel>) {
        adapterBill.setData(wishlist)
        binding.recycleWishlist.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterBill
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recycleWishlist.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        StatusBarUtil.forceStatusBar(requireActivity().window, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
    }

    override fun onResume() {
        super.onResume()
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
    }

    override fun onDeleteItem(item: Any) {
        deleteFunc(item as WishlistModel)
    }

    override fun onMove2BasketClick(item: Any) {
        activity?.supportFragmentManager?.wishlistNavigator(
            EditWishlistFragment.newInstance(
                item as WishlistModel
            )
        )
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

    companion object {
        @JvmStatic
        fun newInstance(param1: WishlistModel, param2: String) =
            WishlistFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}