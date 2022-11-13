package com.alexis.shop.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.R
import com.alexis.shop.data.source.dummy.getTermList
import com.alexis.shop.databinding.FragmentTermsNConditionsBinding
import com.alexis.shop.ui.account.adapter.TermItemList
import com.alexis.shop.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import eightbitlab.com.blurview.RenderScriptBlur

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TermsNConditionsFragment : Fragment(R.layout.fragment_terms_n_conditions) {
    private var param1: String? = null
    private var param2: String? = null

    private val binding: FragmentTermsNConditionsBinding by viewBinding()
    private val termAdapter = GroupAdapter<GroupieViewHolder>()

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
        getTermList().map {
            termAdapter.add(TermItemList(it))
        }

        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnCancel.setOnClickListener {
                justOut()
            }

            with(rvText) {
                adapter = termAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }

            //noinspection AndroidLintSetTextI18n
            txtWl.text = when(param1){
                "1" -> "TERMS AND CONDITIONS"
                else -> "PRIVACY POLICY"
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
            TermsNConditionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}