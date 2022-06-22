package com.alexis.shop.ui.menu.helpcenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.ui.account.FAQFragment
import com.alexis.shop.ui.menu.adapter.MenuAdapter
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.menuNavigator
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.toast

class HelpCenterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var listmenu: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_help_center, container, false)
        cancel_button   = root.findViewById(R.id.btn_cancel)
        listmenu        = root.findViewById(R.id.recycle_listmenu)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        //List Menu Help Center
        val arraymenu: ArrayList<MenuModel> = ArrayList()
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_cloting,"Product"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_buying_guide,"Buying Guide"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_delivery_menu,"Delivery"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_wallet,"Payment"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_order_menu,"Order"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_voucher,"Voucher"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_return_menu,"Return"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_people,"Account"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_feedback,"Feedback"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_career,"Career"))

        listmenu.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MenuAdapter(requireContext(), arraymenu, object : OnClickItem {
                override fun onClick(item: Any) {
                    //Control Action Menu HelpCenter Clicked
                    item as MenuModel
                    val childFragmentManager = requireActivity().supportFragmentManager
                    when(item.title) {
                        "Account" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Account", ""))
                        "Return" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Return", ""))
                        "Order" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Order", ""))
                        "Voucher" ->childFragmentManager.menuNavigator(FAQFragment.newInstance("Voucher", ""))
                        "Product" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Product", ""))
                        "Buying Guide" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Buying Guide", ""))
                        "Delivery" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Delivery", ""))
                        "Payment" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Payment", ""))
                        "Feedback" -> context?.toast("This menu isn't available yet!")
                        "Career" -> context?.toast("This menu isn't available yet!")
                        else -> context?.toast("something went wrong")
                    }
                }
            })
        }
    }

    companion object {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HelpCenterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
