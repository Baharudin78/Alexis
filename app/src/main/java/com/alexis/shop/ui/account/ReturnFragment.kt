package com.alexis.shop.ui.account

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.R
import com.alexis.shop.domain.model.order.ReturnModel
import com.alexis.shop.databinding.FragmentReturnBinding
import com.alexis.shop.ui.menu.adapter.ReturnAdapter
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import com.alexis.shop.utils.OnReturnClickItem
import com.alexis.shop.utils.resetLottieAnimation
import eightbitlab.com.blurview.RenderScriptBlur

class ReturnFragment : Fragment(R.layout.fragment_return) {
    private var param1: String? = null
    private var param2: String? = null

    private val binding: FragmentReturnBinding by viewBinding()
    private var arrayItem = ArrayList<ReturnModel>()
    lateinit var adapterItem: ReturnAdapter

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
        blurView()
        binding.submitglide.resetLottieAnimation()

        binding.btnBack.setOnClickListener {
//            val fragment = when(param2){
//                "DetailOrder" ->  DetailOrderFragment.newInstance("","")
//                else ->  MyAccountFragment.newInstance("","")
//            }
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            justOut()
        }

        binding.edAlias.isEnabled = false

        arrayItem = ArrayList()
        arrayItem.add(ReturnModel("Sweater", false, false, ""))
        arrayItem.add(ReturnModel("Jas", false, false, ""))
        arrayItem.add(ReturnModel("Kemeja", false, false, ""))
        arrayItem.add(ReturnModel("Baju", false, false, ""))

        adapterItem = ReturnAdapter(requireContext(), arrayItem, object: OnReturnClickItem {
            override fun onClick(item: Any) {
                setScaled(item as ReturnModel)
            }

            override fun onLongClick(item: Any, reason: String) {
                item as ReturnModel
                setSelected(item, reason)
            }
        })

        binding.recycleReturn.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterItem
        }

        binding.apply {
            ship1.setOnClickListener { changer(ship1) }
            ship2.setOnClickListener { changer(ship2) }
            ship3.setOnClickListener { changer(ship3) }
            ship4.setOnClickListener { changer(ship4) }
            ship5.setOnClickListener { changer(ship5) }
            ship6.setOnClickListener { changer(ship6) }
            ship7.setOnClickListener { changer(ship7) }
            ship8.setOnClickListener { changer(ship8) }
            ship9.setOnClickListener { changer(ship9) }
        }

    }

    private fun changer(v: View){
        val allShip: ArrayList<TextView> = ArrayList()
        binding.apply {
            allShip.add(ship1)
            allShip.add(ship2)
            allShip.add(ship3)
            allShip.add(ship4)
            allShip.add(ship5)
            allShip.add(ship6)
            allShip.add(ship7)
            allShip.add(ship8)
            allShip.add(ship9)

            allShip.forEach { item ->
                when(v){
                    item -> boldTextChoosen(item)
                    else -> normalTextChoosen(item)
                }
            }
        }
    }

    private fun boldTextChoosen(tv: TextView) {
        tv.setTextAppearance(requireActivity(), R.style.boldText)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(requireContext().resources.getColor(R.color.white, requireContext().theme))
        } else {
            tv.setTextColor(requireContext().resources.getColor(R.color.white))
        }
        tv.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounder_shipping_return)
    }

    private fun normalTextChoosen(title: TextView) {
        title.setTextAppearance(requireActivity(), R.style.lightText)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            title.setTextColor(requireContext().resources.getColor(R.color.white1, requireContext().theme))
        } else {
            title.setTextColor(requireContext().resources.getColor(R.color.white1))
        }
        title.background = null
    }


    fun setScaled(item: ReturnModel) {
        val ccc: ArrayList<ReturnModel> = ArrayList()
        for (a in arrayItem) {
            if(a == item){
                if(item.getScaled()){
                    a.setScaled(false)
                } else {
                    a.setScaled(true)
                    a.setSelected(false)
                }
            } else {
                a.setScaled(false)
            }
            ccc.add(a)
        }
        arrayItem.clear()
        arrayItem.addAll(ccc)
        adapterItem.notifyDataSetChanged()
    }

    fun setSelected(item: ReturnModel, reason: String){
        val ccc: ArrayList<ReturnModel> = ArrayList()
        for (a in arrayItem) {
            if(a == item){
                if(a.getSelected()){
                    a.setSelected(false)
                }else {
                    a.setSelected(true)
                    a.setReason(reason)
                }
            }
            a.setScaled(false)
            ccc.add(a)
        }
        arrayItem.clear()
        arrayItem.addAll(ccc)
        adapterItem.notifyDataSetChanged()
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
        fun newInstance(param1: String, param2: String) =
            ReturnFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}