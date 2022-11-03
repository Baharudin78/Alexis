package com.alexis.shop.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.response.product.categoritwo.ProductCategoryItem
import com.alexis.shop.data.source.dummy.DataModel
import com.alexis.shop.data.source.dummy.ItemAdapter
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.data.source.dummy.getMenuList
import com.alexis.shop.data.source.network.getProductCategory
import com.alexis.shop.databinding.FragmentMenuBinding
import com.alexis.shop.domain.model.product.category.ProductCategoryNewModel
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.ui.account.MyAccountFragment
import com.alexis.shop.ui.account.login.LoginFragment
import com.alexis.shop.ui.main.MainActivity
import com.alexis.shop.ui.main.MainViewModel
import com.alexis.shop.ui.menu.scanqr.ScanQrFragment.Companion.MENU_FRAGMENT
import com.alexis.shop.ui.menu.aboutus.AboutUsFragment
import com.alexis.shop.ui.menu.adapter.category.ProductCategoryNewAdapter
import com.alexis.shop.ui.menu.adapter.categoryproduct.CategoryProductAdapter
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
class MenuFragment : BaseFragment<FragmentMenuBinding>(), OnClickItem{
    private val viewModel: MenuViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoryProduct = ArrayList<ProductCategoryNewModel>()
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapterCategory: ProductCategoryNewAdapter
    private val menuAdapter = GroupAdapter<GroupieViewHolder>()
    private val sosmedAdapter = GroupAdapter<GroupieViewHolder>()
    private var listMenu: ArrayList<MenuModel> = ArrayList()
    private var fragManager: FragmentManager? = null


    override fun getViewBinding() = FragmentMenuBinding.inflate(layoutInflater)

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

    override fun main() {
        fragManager = activity?.supportFragmentManager
        adapterCategory = ProductCategoryNewAdapter(binding.root.context, this)
        with(binding.rvcategory) {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = adapterCategory
        }
        getProductCategory()
        menuAdapter.clear()
        sosmedAdapter.clear()
        initMenu()
        setListener()

    }

    private fun initMenu() {
        listMenu = getMenuList(viewModel.isUserLogin())
        addDataMenuAdapter()
        addDataSosmedAdapter()
    }
    private fun getProductCategory() {
        mainViewModel.getProductCategory().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.e("RemoteDataS", "1")
                        Log.e("RemoteDataS", "${response.data}")
                        val categoryProductValue = response.data as ArrayList<ProductCategoryNewModel>
                        categoryProduct = categoryProductValue
                        adapterCategory.setDataProduct(categoryProductValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            "GetFailed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setListener(){
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

    private fun addDataMenuAdapter() {
        listMenu.slice(0..7).map { menu ->
            menu.isOpen = true
            menuAdapter.add(MenuItem(this, menu) {
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

    private fun addDataSosmedAdapter() {
        listMenu.slice(8..12).map { sosmed ->
            sosmed.isOpen = true
            sosmedAdapter.add(SocialItem(requireContext(), sosmed))
        }
    }

    private fun closeMenu() {
        removeList()
        listMenu.clear()
        withDelay(1000) {
            binding.recycleListmenu.gone()
            justOut()
        }
    }

    private fun removeList() {
        menuAdapter.clear()
        sosmedAdapter.clear()
        listMenu.slice(0..7).map { menu ->
            menu.isOpen = false
            menu.isClose = true
            menuAdapter.add(MenuItem(this, menu) {
                log(it.title)
            })
        }

        listMenu.slice(8..12).map { sosmed ->
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
            MenuFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(item: Any) {

    }
}