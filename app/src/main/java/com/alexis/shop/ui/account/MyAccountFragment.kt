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
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.ui.account.adapter.MyAccountAdapter
import com.alexis.shop.ui.account.profile.ProfileFragment
import com.alexis.shop.ui.account.voucher.VoucherFragment
import com.alexis.shop.ui.menu.address.ChangeAddressFragment
import com.alexis.shop.ui.menu.language.LanguageFragment
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.utils.accountNavigator
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.toast
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MyAccountFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var terms: TextView
    lateinit var privacy: TextView
    lateinit var listmenu: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        enterTransition = MaterialFadeThrough()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_my_account, container, false)
        cancel_button   = root.findViewById(R.id.btn_cancel)
        listmenu        = root.findViewById(R.id.recycle_myacc)
        terms           = root.findViewById(R.id.t1)
        privacy         = root.findViewById(R.id.t2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancel_button.setOnClickListener {
            requireActivity().supportFragmentManager.accountNavigator(MenuFragment.newInstance("","222"))
        }

        terms.setOnClickListener {
            requireActivity().supportFragmentManager.accountNavigator(TermsNConditionsFragment.newInstance("1",""))
        }

        privacy.setOnClickListener {
            requireActivity().supportFragmentManager.accountNavigator(TermsNConditionsFragment.newInstance("2",""))
        }

        //List Menu
        val arraymenu: ArrayList<MenuModel> = ArrayList()
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_order_menu,"Order"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_point,"Point"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_voucher,"Voucher"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_profile_menu,"Profile"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_location_pin,"Address"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_language,"Language"))
        arraymenu.add(MenuModel(1, 1, R.drawable.ic_logout,"Logout"))

        listmenu.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyAccountAdapter(requireContext(), arraymenu, object : OnClickItem {
                override fun onClick(item: Any) {
                    val childFragmentManager = requireActivity().supportFragmentManager
                    //Action on Menu Clicked
                    when ((item as MenuModel).title) {
                        "Order" -> {
                            childFragmentManager.accountNavigator(OrderFragment.newInstance("",""))
                        }
                        "Point" -> {
                            childFragmentManager.accountNavigator(PointsFragment.newInstance("",""))
                        }
                        "Voucher" -> {
                            childFragmentManager.accountNavigator(VoucherFragment.newInstance("",""))
                        }
                        "Profile" -> {
                            childFragmentManager.accountNavigator(ProfileFragment.newInstance("",""))
                        }
                        "Address" -> {
                            childFragmentManager.accountNavigator(ChangeAddressFragment.newInstance("",""))
                        }
                        "Language" -> {
                            childFragmentManager.accountNavigator(LanguageFragment.newInstance("",""))
                        }
                        "Logout" -> {
                            childFragmentManager.accountNavigator(MenuFragment.newInstance("","111"))
                            requireContext().toast("Logout Successfully")
                        }
                    }
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MyAccountFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}