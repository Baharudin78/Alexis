package com.alexis.shop.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.ui.menu.adapter.PointsAdapter
import com.alexis.shop.utils.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PointsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView
    lateinit var title: TextView
    lateinit var recycle: RecyclerView

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
        val root = inflater.inflate(R.layout.fragment_points, container, false)
        cancel_button   = root.findViewById(R.id.btn_cancel)
        back_button   = root.findViewById(R.id.btn_back)
        title = root.findViewById(R.id.txt_wl)
        recycle = root.findViewById(R.id.recycle_points)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancel_button.setOnClickListener {
            justOut()
        }
        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        val arrayDate: ArrayList<String> = ArrayList()
        arrayDate.add("20 Jan 21")
        arrayDate.add("20 Nov 20")
        arrayDate.add("20 Sep 20")
        arrayDate.add("20 Jul 20")
        arrayDate.add("20 Mar 20")

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PointsAdapter(requireContext(), arrayDate, object : OnClickItem {
                override fun onClick(item: Any) {
                    item as String

                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PointsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}