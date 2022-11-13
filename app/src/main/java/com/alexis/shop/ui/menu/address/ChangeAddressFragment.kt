package com.alexis.shop.ui.menu.address

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
import com.alexis.shop.R
import com.alexis.shop.ui.account.MyAccountFragment
import com.alexis.shop.ui.menu.address.AddAddressFragment.Companion.CHANGE_ADDRESS
import com.alexis.shop.ui.menu.adapter.ChangeAddressAdapter
import com.alexis.shop.utils.*
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_change_address.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChangeAddressFragment : Fragment(), OnClickItem {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView
    lateinit var add_button: ConstraintLayout
    lateinit var title: TextView
    lateinit var recycle: RecyclerView

    private lateinit var addressAdapter: ChangeAddressAdapter
    private var arrayDate: ArrayList<String> = ArrayList()

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
        val root = inflater.inflate(R.layout.fragment_change_address, container, false)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)
        add_button = root.findViewById(R.id.buttonAddAddress)
        title = root.findViewById(R.id.txt_wl)
        recycle = root.findViewById(R.id.recycle_address)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blurView()
        cancel_button.setOnClickListener {
            justOut()
        }
        back_button.setOnClickListener {
            val fragment = MyAccountFragment.newInstance("", "")
            requireActivity().supportFragmentManager.accountNavigator(fragment)
        }
        add_button.setOnClickListener {
            val fragment = AddAddressFragment.newInstance(CHANGE_ADDRESS, "")
            requireActivity().supportFragmentManager.accountNavigator(fragment)
        }

        //Array using sample Address Name
        arrayDate.add("Office")
        arrayDate.add("Home")
        arrayDate.add("Kost A")
        arrayDate.add("Kost B")
        arrayDate.add("Kost C")

        addressAdapter = ChangeAddressAdapter(arrayDate, listener = this, onDeleteClick = ::deleteItem)

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = addressAdapter
        }
    }

    private fun deleteItem(position: Int) {
        arrayDate.removeAt(position)
        addressAdapter.notifyDataSetChanged()
    }

    override fun onClick(item: Any) {
        log(item.toString())
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
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeAddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}