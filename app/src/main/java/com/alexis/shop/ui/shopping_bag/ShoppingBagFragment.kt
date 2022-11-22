package com.alexis.shop.ui.shopping_bag

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentShoppingBagBinding
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.ui.checkout.SelectAddressFragment
import com.alexis.shop.ui.shopping_bag.adapter.ShoppingBagAdapter
import com.alexis.shop.utils.*
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_shopping_bag.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ShoppingBagFragment : BaseFragment<FragmentShoppingBagBinding>(), OnShoppingBagClickItem {

    override fun getViewBinding() = FragmentShoppingBagBinding.inflate(layoutInflater)
    private val viewModel: ShoppingBagViewModel by viewModels()
    lateinit var adapterBill: ShoppingBagAdapter
    private var shoppingBagList = ArrayList<ShoppingBagModel>()
    private var fragManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
        super.onCreate(savedInstanceState)
        handleBackPressed()
        enterTransition = MaterialFadeThrough()
//        exitTransition = MaterialFadeThrough()
    }

    override fun main() {
        super.main()
        blurView()
        adapterBill = ShoppingBagAdapter(binding.root.context, this)
        with(binding.recycleShoppingbag) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterBill
        }
        getShoppingBag()
        setListener()
       // postWishlist()
    }
    private fun setListener() {
        val childFragmentManager = requireActivity().supportFragmentManager
        binding.submit.setOnClickListener {
            childFragmentManager.accountNavigator(SelectAddressFragment())
        }
    }
    private fun deleteFunc(item: ShoppingBagModel) {
        shoppingBagList.remove(item)
        adapterBill.setData(shoppingBagList)
    }

    private fun getShoppingBag() {
            viewModel.getShoppingCart().observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val shoppingValue = response.data?.bag as ArrayList<ShoppingBagModel>
                            shoppingBagList = shoppingValue
                            adapterBill.setData(shoppingValue)
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext().applicationContext,
                                getString(R.string.auth_error, "Get Shopping Bag"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
    }

    private fun deleteShoppingBag(item: ShoppingBagModel) {
        viewModel.deleteShoppingBag(item.id.orZero()).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> deleteFunc(item)
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext().applicationContext,
                            getString(R.string.auth_error, "Delete shopping bag"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun postWishlist(itemCode: String) {
        viewModel.postWishlist(itemCode).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        requireContext().toast("Added to Wishlist")
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext().applicationContext,
                            getString(R.string.auth_error, "Post Wishlist"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
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
            ShoppingBagFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDeleteItem(item: Any) {
        TODO("Not yet implemented")
    }

    override fun onMove2WishList(item: Any) {
        item as ShoppingBagModel
        postWishlist(item.product_item_code)
    }

    override fun onEditItem(item: Any) {
        TODO("Not yet implemented")
    }
}