package com.alexis.shop.ui.account

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.R
import com.alexis.shop.data.source.dummy.TrackingList
import com.alexis.shop.databinding.FragmentDetailOrderBinding
import com.alexis.shop.ui.account.adapter.TrackingItemList
import com.alexis.shop.ui.menu.adapter.OrderDetailAdapter
import com.alexis.shop.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import eightbitlab.com.blurview.RenderScriptBlur

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class  DetailOrderFragment : Fragment(R.layout.fragment_detail_order) {

    private val binding: FragmentDetailOrderBinding by viewBinding()
    private val trackingAdapter = GroupAdapter<GroupieViewHolder>()

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoReturnGuide = "<a>You may return the product within 3 days. See <strong>Return Guide.</strong></a>"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.infoDetailOrder.text = Html.fromHtml(infoReturnGuide, Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.infoDetailOrder.text = Html.fromHtml(infoReturnGuide)
        }
        blurView()

        TrackingList.getTrackingList().map {
            trackingAdapter.add(TrackingItemList(it))
        }

        binding.apply {

            with(trackingList) {
                adapter = trackingAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnCancel.setOnClickListener {
                justOut()
            }

            infoDetailOrder.setOnClickListener {
                requireActivity().supportFragmentManager.accountNavigator(FAQFragment.newInstance("Return"))
            }

            btnReturnGuide.setOnClickListener {
                requireActivity().supportFragmentManager.accountNavigator(ReturnFragment.newInstance("","DetailOrder"))
            }
        }

        val loc: ArrayList<String> = ArrayList()
        loc.add("Sweater")
        loc.add("Backpack")
        loc.add("Dress")

        binding.orderList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = OrderDetailAdapter(requireContext(), loc)
        }


        var opened = false
        binding.apply {
            tracking.setOnClickListener {
                //Control Up/Down Icon on expantion track information
                if(!opened){
                    opened = true
                    trackingList.visible()
                    trackingNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_iconup, 0)
                }else{
                    opened = false
                    trackingList.gone()
                    trackingNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icondown, 0)
                }
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}