package com.alexis.shop.ui.shopping_bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.alexis.shop.R
import com.alexis.shop.ui.menu.adapter.VoucherAdapter
import com.alexis.shop.ui.menu.adapter.VOUCHER_SELECTOR_FRAGMENT
import com.alexis.shop.utils.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SelectVoucherFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var title: TextView
    lateinit var recycle: RecyclerView
    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView
    lateinit var image_button: LottieAnimationView

    lateinit var btn_submit: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        handleBackPressed()
//        enterTransition = MaterialFadeThrough()
//        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_select_voucher, container, false)
        title = root.findViewById(R.id.txt_wl)
        recycle = root.findViewById(R.id.recycle_voucher)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)

        btn_submit = root.findViewById(R.id.btn_submit)
        image_button = root.findViewById(R.id.gilding)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_button.resetLottieAnimation()

        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        cancel_button.setOnClickListener {
            justOut()
        }

        //Sample List Voucher
        val itemList: ArrayList<String> = ArrayList()
        itemList.add("Shipping Refund")
        itemList.add("Birthday")
        itemList.add("Voucher A")
        itemList.add("Voucher Z")

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = VoucherAdapter(VOUCHER_SELECTOR_FRAGMENT, itemList, object: OnClickItem {
                override fun onClick(item: Any) {
                    item as String
                }
            })
        }

        btn_submit.setOnClickListener {
            val fragment = SelectPromoOrVoucherFragment.newInstance("","")
            requireActivity().supportFragmentManager.shopNavigator(fragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SelectVoucherFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}