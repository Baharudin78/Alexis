package com.alexis.shop.ui.account

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
import com.alexis.shop.ui.menu.adapter.OrderAdapter
import com.alexis.shop.utils.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OrderFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var title: TextView
    lateinit var recycle: RecyclerView
    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView

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
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        title = root.findViewById(R.id.txt_wl)
        recycle = root.findViewById(R.id.recycle_order)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener {
            val fragment = MyAccountFragment.newInstance("","")
            requireActivity().supportFragmentManager.popBackStack()
        }

        cancel_button.setOnClickListener {
            justOut()
        }
        val loc: ArrayList<String> = ArrayList()
        loc.add("26 Jan 21")
        loc.add("17 Nov 20")
        loc.add("02 Okt 20")
        loc.add("30 Sep 20")
        loc.add("08 Agu 20")
        loc.add("20 Jul 20")

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = OrderAdapter(requireContext(), loc, object: OnClickItem {
                override fun onClick(item: Any) {
                    item as String
                    val fragment = DetailOrderFragment.newInstance("","")
                    requireActivity().supportFragmentManager.accountNavigator(fragment)
                }
            })
        }
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