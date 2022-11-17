package com.alexis.shop.ui.account.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentOrderBinding
import com.alexis.shop.domain.model.order.OrderItemModel
import com.alexis.shop.ui.account.DetailOrderFragment
import com.alexis.shop.ui.account.MyAccountFragment
import com.alexis.shop.ui.menu.adapter.OrderAdapter
import com.alexis.shop.utils.*
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_order.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>(), OnClickItem {
    private var param1: String? = null
    private var param2: String? = null
//
//    lateinit var title: TextView
//    lateinit var recycle: RecyclerView
//    lateinit var cancel_button: ImageView
//    lateinit var back_button: ImageView
    private val viewModel : OrderViewModel by viewModels()
    lateinit var adapterOrder : OrderAdapter
    private var orderList = ArrayList<OrderItemModel>()
    override fun getViewBinding() = FragmentOrderBinding.inflate(layoutInflater)

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
        adapterOrder = OrderAdapter(binding.root.context, this)
        with(binding.recycleOrder) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterOrder
        }
        getOrder()
    }
    private fun getOrder() {
        viewModel.getOrder().observe(viewLifecycleOwner) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val orderValue = response.data?.order as ArrayList<OrderItemModel>
                        orderList = orderValue
                        adapterOrder.setData(orderValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "${response.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_order, container, false)
//        title = root.findViewById(R.id.txt_wl)
//        recycle = root.findViewById(R.id.recycle_order)
//        cancel_button = root.findViewById(R.id.btn_cancel)
//        back_button = root.findViewById(R.id.btn_back)
//
//        return root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        blurView()
//        back_button.setOnClickListener {
//            val fragment = MyAccountFragment.newInstance("","")
//            requireActivity().supportFragmentManager.popBackStack()
//        }
//
//        cancel_button.setOnClickListener {
//            justOut()
//        }
//        val loc: ArrayList<String> = ArrayList()
//        loc.add("26 Jan 21")
//        loc.add("17 Nov 20")
//        loc.add("02 Okt 20")
//        loc.add("30 Sep 20")
//        loc.add("08 Agu 20")
//        loc.add("20 Jul 20")
//
//        recycle.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = OrderAdapter(requireContext(), loc, object: OnClickItem {
//                override fun onClick(item: Any) {
//                    item as String
//                    val fragment = DetailOrderFragment.newInstance("","")
//                    requireActivity().supportFragmentManager.accountNavigator(fragment)
//                }
//            })
//        }
//    }

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

    override fun onClick(item: Any) {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}