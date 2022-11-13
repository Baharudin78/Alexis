package com.alexis.shop.ui.menu.helpcenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentHelpCenterBinding
import com.alexis.shop.domain.model.helpcenter.HelpCenterItemModel
import com.alexis.shop.domain.model.helpcenter.HelpCenterModel
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.ui.account.FAQFragment
import com.alexis.shop.ui.menu.adapter.MenuAdapter
import com.alexis.shop.ui.menu.adapter.helpcenter.HelpCenterAdapter
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.menuNavigator
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.item_product_category_new.*

@AndroidEntryPoint
class HelpCenterFragment : BaseFragment<FragmentHelpCenterBinding>(), OnClickItem {
    private val viewModel : HelpCenterViewModel by viewModels()
    private lateinit var helpCenterAdapter : HelpCenterAdapter
    private var helpCenterModel = ArrayList<HelpCenterItemModel>()
    lateinit var cancel_button: ImageView

    override fun getViewBinding() =  FragmentHelpCenterBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    override fun main() {
        super.main()
        blurView()
        helpCenterAdapter = HelpCenterAdapter(binding.root.context, this)
        with(binding.recycleListmenu) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = helpCenterAdapter
        }
        getHelpCenter()
        setListener()
    }

    private fun getHelpCenter() {
        viewModel.getHelpCenter().observe(viewLifecycleOwner) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val helpCenterValue = response.data?.helpCenter as ArrayList<HelpCenterItemModel>
                        helpCenterModel = helpCenterValue
                        helpCenterAdapter.setDataHelp(helpCenterValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(binding.root.context, "Failed Get Helpcenter", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            btnCancel.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_help_center, container, false)
//        cancel_button   = root.findViewById(R.id.btn_cancel)
//        listmenu        = root.findViewById(R.id.recycle_listmenu)
//        return root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        cancel_button.setOnClickListener {
//            requireActivity().supportFragmentManager.popBackStack()
//        }
//
//        //List Menu Help Center
//        val arraymenu: ArrayList<MenuModel> = ArrayList()
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_cloting,"Product"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_buying_guide,"Buying Guide"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_delivery_menu,"Delivery"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_wallet,"Payment"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_order_menu,"Order"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_voucher,"Voucher"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_return_menu,"Return"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_people,"Account"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_feedback,"Feedback"))
//        arraymenu.add(MenuModel(1, 1, R.drawable.ic_career,"Career"))
//
//        listmenu.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = MenuAdapter(requireContext(), arraymenu, object : OnClickItem {
//                override fun onClick(item: Any) {
//                    //Control Action Menu HelpCenter Clicked
//                    item as MenuModel
//                    val childFragmentManager = requireActivity().supportFragmentManager
//                    when(item.title) {
//                        "Account" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Account", ""))
//                        "Return" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Return", ""))
//                        "Order" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Order", ""))
//                        "Voucher" ->childFragmentManager.menuNavigator(FAQFragment.newInstance("Voucher", ""))
//                        "Product" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Product", ""))
//                        "Buying Guide" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Buying Guide", ""))
//                        "Delivery" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Delivery", ""))
//                        "Payment" -> childFragmentManager.menuNavigator(FAQFragment.newInstance("Payment", ""))
//                        "Feedback" -> context?.toast("This menu isn't available yet!")
//                        "Career" -> context?.toast("This menu isn't available yet!")
//                        else -> context?.toast("something went wrong")
//                    }
//                }
//            })
//        }
//    }

    override fun onClick(item: Any) {
        item as HelpCenterItemModel
        requireActivity().supportFragmentManager.menuNavigator(
            FAQFragment.newInstance(item.name)
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
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance() : HelpCenterFragment{
            return HelpCenterFragment()
        }
    }
}
