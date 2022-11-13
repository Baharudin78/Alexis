package com.alexis.shop.ui.menu.referandearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.ui.menu.adapter.ReferAndEarnAdapter
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_refer_and_earn.*

class ReferAndEarnFragment : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView
    lateinit var title: TextView
    lateinit var recycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        if (arguments != null) {
            mParam1 = arguments?.getString(ARG_PARAM1)
            mParam2 = arguments?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_refer_and_earn, container, false)
        cancel_button   = root.findViewById(R.id.btn_cancel)
        back_button     = root.findViewById(R.id.btn_back)
        title           = root.findViewById(R.id.txt_wl)
        recycle         = root.findViewById(R.id.recycle_referearn)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = "Refer & Earn"
        blurView()
        cancel_button.setOnClickListener {
            justOut()
        }
        back_button.setOnClickListener {
            val fragment = MenuFragment.newInstance("", "111")
            requireActivity().supportFragmentManager.popBackStack()
        }

        val arrayReferal: ArrayList<String> = ArrayList()
        arrayReferal.add("Martha")
        arrayReferal.add("Kim")
        arrayReferal.add("Santi")
        arrayReferal.add("Mega")
        arrayReferal.add("Daniel")
        arrayReferal.add("Rina")
        arrayReferal.add("Donna")
        arrayReferal.add("Tuti")
        arrayReferal.add("Rama")
        arrayReferal.add("Michael")

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ReferAndEarnAdapter(viewLifecycleOwner, arrayReferal)
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

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(
            param1: String?,
            param2: String?
        ): ReferAndEarnFragment {
            val fragment =
                ReferAndEarnFragment()
            val args = Bundle()
            args.putString(
                ARG_PARAM1,
                param1
            )
            args.putString(
                ARG_PARAM2,
                param2
            )
            fragment.arguments = args
            return fragment
        }
    }
}