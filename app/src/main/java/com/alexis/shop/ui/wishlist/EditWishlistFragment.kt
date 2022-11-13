package com.alexis.shop.ui.wishlist

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.wishlist.WishlistModel
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.wishlistNavigator
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_menu.*

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class EditWishlistFragment : Fragment() {
    private var wishlistModel: WishlistModel? = null
    private val viewModel: WishlistViewModel by viewModels()
    lateinit var back_btn: ImageView
    lateinit var whole_item: ConstraintLayout
    lateinit var size_xs: TextView
    lateinit var size_s: TextView
    lateinit var size_m: TextView
    lateinit var size_l: TextView
    lateinit var size_xl: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            wishlistModel = it.getSerializable(ARG_PARAM1) as? WishlistModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_edit_wishlist, container, false)
        back_btn = root.findViewById(R.id.btn_cancel2)
        whole_item = root.findViewById(R.id.whole_item)
        size_xs = root.findViewById(R.id.size_xs)
        size_s = root.findViewById(R.id.size_s)
        size_m = root.findViewById(R.id.size_m)
        size_l = root.findViewById(R.id.size_l)
        size_xl = root.findViewById(R.id.size_xl)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blurView()
        back_btn.setOnClickListener {
            val rtc = AnimationUtils.loadAnimation(requireContext(), R.anim.to_top_left)
            whole_item.startAnimation(rtc)

            withDelay(900) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        size_xs.setOnClickListener {
            boldTextsizeChoosen(size_xs)
            val unchoose =
                    arrayOf<TextView>(size_s, size_m, size_l, size_xl)
            normalTextUnchoosen(unchoose)
            postShoppingBag("XS")
        }
        size_s.setOnClickListener {
            boldTextsizeChoosen(size_s)
            val unchoose =
                    arrayOf<TextView>(size_xs, size_m, size_l, size_xl)
            normalTextUnchoosen(unchoose)
            postShoppingBag("S")
        }
        size_m.setOnClickListener {
            boldTextsizeChoosen(size_m)
            val unchoose =
                    arrayOf<TextView>(size_xs, size_s, size_l, size_xl)
            normalTextUnchoosen(unchoose)
            postShoppingBag("M")
        }
        size_l.setOnClickListener {
            boldTextsizeChoosen(size_l)
            val unchoose =
                    arrayOf<TextView>(size_s, size_m, size_xs, size_xl)
            normalTextUnchoosen(unchoose)
            postShoppingBag("L")
        }
        size_xl.setOnClickListener {
            boldTextsizeChoosen(size_xl)
            val unchoose =
                    arrayOf<TextView>(size_s, size_m, size_l, size_xs)
            normalTextUnchoosen(unchoose)
            postShoppingBag("XL")
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

    private fun postShoppingBag(size: String) {
//        activity?.let {
//            viewModel.postShoppingBag(wishlistModel?.productItemCode.toString(), wishlistModel.product.product_size_id.toString(),).observe(viewLifecycleOwner) { response ->
//                if (response != null) {
//                    when (response) {
//                        is Resource.Loading -> {}
//                        is Resource.Success -> {
//                            withDelay(900) {
//                                val fragment = WishlistFragment.newInstance(wishlistModel!!,"")
//                                it.supportFragmentManager.wishlistNavigator(fragment)
//                            }
//                        }
//                        is Resource.Error -> {
//                            Toast.makeText(
//                                it.applicationContext,
//                                getString(R.string.auth_error, "Post Shopping Bag"),
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//            }
//        }
    }

    private fun boldTextsizeChoosen(tv: TextView) {
        tv.setTextAppearance(requireContext(), R.style.boldText)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(
                requireContext().resources
                    .getColor(R.color.alexis_orange, requireContext().theme)
            )
        } else {
            tv.setTextColor(requireContext().resources.getColor(R.color.alexis_orange))
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)

        val rtc = AnimationUtils.loadAnimation(requireContext(), R.anim.to_top_left)
        whole_item.startAnimation(rtc)
    }

    private fun normalTextUnchoosen(tv: Array<TextView>) {
        for (title in tv) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                title.setTextColor(
                    requireContext().resources
                        .getColor(R.color.white, requireContext().theme)
                )
            } else {
                title.setTextColor(
                    requireContext().resources.getColor(R.color.grey_700)
                )
            }
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: WishlistModel) =
                EditWishlistFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                    }
                }
    }
}