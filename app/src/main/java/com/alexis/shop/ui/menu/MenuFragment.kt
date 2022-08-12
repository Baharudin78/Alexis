package com.alexis.shop.ui.menu

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.data.source.dummy.getMenuList
import com.alexis.shop.databinding.FragmentMenuBinding
import com.alexis.shop.ui.account.MyAccountFragment
import com.alexis.shop.ui.account.login.LoginFragment
import com.alexis.shop.ui.main.MainActivity
import com.alexis.shop.ui.main.MainViewModel
import com.alexis.shop.ui.menu.scanqr.ScanQrFragment.Companion.MENU_FRAGMENT
import com.alexis.shop.ui.menu.aboutus.AboutUsFragment
import com.alexis.shop.ui.menu.adapter.item.MenuItem
import com.alexis.shop.ui.menu.adapter.item.SocialItem
import com.alexis.shop.ui.menu.contactus.ContactUsFragment
import com.alexis.shop.ui.menu.helpcenter.HelpCenterFragment
import com.alexis.shop.ui.menu.referandearn.ReferAndEarnFragment
import com.alexis.shop.ui.menu.scanqr.ScanQrFragment
import com.alexis.shop.ui.menu.sizefilter.SizeFilterFragment
import com.alexis.shop.ui.menu.storelocation.StoreLocationHomeFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelay
import com.google.android.material.transition.MaterialFadeThrough
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val viewModel: MenuViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null

    private val binding: FragmentMenuBinding by viewBinding()
    private val menuAdapter = GroupAdapter<GroupieViewHolder>()
    private val sosmedAdapter = GroupAdapter<GroupieViewHolder>()
    private var listMenu: ArrayList<MenuModel> = ArrayList()
    private var fragManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        isOpen = when(param2) {
//            "222" -> true
//            "111" -> true
//            else -> true
//        }

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeMenu()
            }
        })

        enterTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragManager = activity?.supportFragmentManager
        menuAdapter.clear()
        sosmedAdapter.clear()
        initMenu()

        binding.apply {
            btnScan.setPushClickListener {
                fragManager?.menuNavigator(ScanQrFragment.newInstance(MENU_FRAGMENT,""))
            }

            btnCancel.setPushClickListener {
                closeMenu()
            }

            recycleListsosmed.apply {
                layoutManager = GridLayoutManager(context, 5)
                adapter = sosmedAdapter
            }

            recycleListmenu.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = menuAdapter
            }

            val anim = loadAnimation(context, R.anim.animate_entrace_fragment).apply {
                duration = (7 * animateEntraceMenuTime).toLong()
            }

            btnScan.startAnimation(anim)
        }
    }

    private fun initMenu() {
        mainViewModel.getProductCategoryData().observe(viewLifecycleOwner) { dataCategory ->
            listMenu = getMenuList(viewModel.isUserLogin(), dataCategory)
            mainViewModel.getSubCategoryData().observe(viewLifecycleOwner) {

            }
            addDataMenuAdapter()
            //addDataSosmedAdapter()
        }
    }

    private fun addDataMenuAdapter() {
        listMenu.slice(0..10).map { menu ->
            menu.isOpen = true
            menuAdapter.add(MenuItem(this, menu) { // onClick
                log("${it.id} ${it.isChoosed}")
                when(it.title) {
                    "Size Filter" -> fragManager?.menuNavigator(SizeFilterFragment())
                    "Help Center" -> fragManager?.menuNavigator(HelpCenterFragment.newInstance("",""))
                    "About Us" -> fragManager?.menuNavigator(AboutUsFragment())
                    "Refer & Earn" -> fragManager?.menuNavigator(ReferAndEarnFragment.newInstance("",""))
                    "Contact Us" -> fragManager?.menuNavigator(ContactUsFragment.newInstance("",""))
                    "Login/Register" -> fragManager?.menuNavigator(LoginFragment())
                    "My Account" -> fragManager?.menuNavigator(MyAccountFragment.newInstance("",""))
                    "Store Location" -> fragManager?.menuNavigator(StoreLocationHomeFragment.newInstance())
                    else -> changeChosen(it)
                }
            })
        }
    }

//    private fun addDataSosmedAdapter() {
//        listMenu.slice(11..15).map { sosmed ->
//            sosmed.isOpen = true
//            sosmedAdapter.add(SocialItem(requireContext(), sosmed))
//        }
//    }

    private fun closeMenu() {
        removeList()
        listMenu.clear()
        withDelay(3000) {
            binding.recycleListmenu.gone()
            justOut()
        }
    }

    private fun removeList() {
        menuAdapter.clear()
        sosmedAdapter.clear()
        listMenu.slice(0..10).map { menu ->
            menu.isOpen = false
            menu.isClose = true
            menuAdapter.add(MenuItem(this, menu) {
                log(it.title)
            })
        }

        listMenu.slice(11..15).map { sosmed ->
            sosmed.isOpen = false
            sosmed.isClose = true
            sosmedAdapter.add(SocialItem(requireContext(), sosmed))
        }
    }

    private fun changeChosen(item: MenuModel) {
        val menus = ArrayList<MenuModel>()
        listMenu.forEach { menu ->
            menu.isOpen = false
            if(menu.id == item.id) {
                menu.isChoosed = !item.isChoosed
            } else {
                menu.isChoosed = false
            }
            menus.add(menu)
        }
        listMenu.clear()
        listMenu.addAll(menus)
        menuAdapter.notifyDataSetChanged()
        sosmedAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.recycleListsosmed.adapter = null
        binding.recycleListmenu.adapter = null
        StatusBarUtil.forceStatusBar(requireActivity().window, false)
        (requireActivity() as MainActivity).option.isChecked = false
    }

    override fun onStart() {
        StatusBarUtil.forceStatusBar(requireActivity().window, true)
        super.onStart()
    }

    companion object {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"
        const val animateEntraceMenuTime = 180

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}