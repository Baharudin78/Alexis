package com.alexis.shop.ui.shopping_bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.shoppingbag.ShoppingBagModel
import com.alexis.shop.ui.checkout.SelectAddressFragment
import com.alexis.shop.ui.shopping_bag.adapter.ShoppingBagAdapter
import com.alexis.shop.utils.*
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ShoppingBagFragment : Fragment() {
    private val viewModel: ShoppingBagViewModel by viewModels()

    lateinit var submit_button: ConstraintLayout
    lateinit var cancel_button: ImageView
    lateinit var recycle_shoppingbag: RecyclerView
    lateinit var image_button: LottieAnimationView

    lateinit var adapterBill: ShoppingBagAdapter
    private var shoppingBagList: ArrayList<ShoppingBagModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
        super.onCreate(savedInstanceState)
        handleBackPressed()
        enterTransition = MaterialFadeThrough()
//        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root    = inflater.inflate(R.layout.fragment_shopping_bag, container, false)
        cancel_button       = root.findViewById(R.id.btn_cancel2)
        submit_button       = root.findViewById(R.id.submit)
        recycle_shoppingbag = root.findViewById(R.id.recycle_shoppingbag)
        image_button        = root.findViewById(R.id.gilding)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getShoppingBag()
        image_button.resetLottieAnimation()

        cancel_button.setOnClickListener {
            justOut()
        }

        submit_button.setOnClickListener {
            val fragment = SelectAddressFragment()
            requireActivity().supportFragmentManager.shopNavigator(fragment)
        }

//        listName.add("Kemeja")
//        listName.add("Kaos")
//        listName.add("Baju")

        adapterBill = ShoppingBagAdapter(object: OnShoppingBagClickItem{
            override fun onDeleteItem(item: Any) {
                deleteShoppingBag(item as ShoppingBagModel)
            }

            override fun onMove2WishList(item: Any) {
                deleteShoppingBag(item as ShoppingBagModel)
                postWishlist(item)
            }

            override fun onEditItem(item: Any) {
                val fragment = EditShoppingBagFragment.newInstance("","")
                requireActivity().supportFragmentManager.shopNavigator(fragment)
            }

        })

        recycle_shoppingbag.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterBill
        }
    }

    private fun deleteFunc(item: ShoppingBagModel) {
        shoppingBagList.remove(item)
        adapterBill.setData(shoppingBagList)
    }

    private fun getShoppingBag() {
        if(viewModel.isUserAuthenticated()) {
            viewModel.getShoppingCart().observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            response.data?.let {
                                shoppingBagList = it as ArrayList<ShoppingBagModel>
                                adapterBill.setData(it)
                            }
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
    }

    private fun deleteShoppingBag(item: ShoppingBagModel) {
        viewModel.deleteShoppingBag(item.shoppingBagId).observe(viewLifecycleOwner) { response ->
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

    private fun postWishlist(item: ShoppingBagModel) {
//        viewModel.postWishlist(item.productId).observe(viewLifecycleOwner) { response ->
//            if (response != null) {
//                when (response) {
//                    is Resource.Loading -> {}
//                    is Resource.Success -> {
//                        requireContext().toast("Added to Wishlist")
//                    }
//                    is Resource.Error -> {
//                        Toast.makeText(
//                            requireContext().applicationContext,
//                            getString(R.string.auth_error, "Post Wishlist"),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
//        }
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
}