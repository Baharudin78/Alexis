package com.alexis.shop.ui.menu.helpcenter.child

import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.databinding.FragmentProductHelpBinding
import com.alexis.shop.ui.menu.helpcenter.HelpCenterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductHelpCenterFragment : BaseFragment<FragmentProductHelpBinding>() {
    private val viewModel : HelpCenterViewModel by viewModels()

    override fun getViewBinding()= FragmentProductHelpBinding.inflate(layoutInflater)
}