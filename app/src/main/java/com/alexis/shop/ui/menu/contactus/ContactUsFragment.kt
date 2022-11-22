package com.alexis.shop.ui.menu.contactus

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentContactUsBinding
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.handleBackPressed
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(), OnClickItem {
    private var contactModel : ContactDataModel? = null
    private val viewModel : ContactViewModel by viewModels()

    override fun getViewBinding() =  FragmentContactUsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun main() {
        super.main()
        blurView()

        getContact()
    }

    private fun getContact() {
        viewModel.getContact().observe(viewLifecycleOwner){response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            contactModel = it
                            setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(binding.root.context, "Failed Get Helpcenter", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupView(data : ContactDataModel) {
        binding.txtWa.text = data.whatsapp.orEmpty()
        binding.txtMail.text = data.email.orEmpty()
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

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ContactUsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

    override fun onClick(item: Any) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }
}