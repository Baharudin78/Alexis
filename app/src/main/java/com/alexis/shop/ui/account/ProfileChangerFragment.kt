package com.alexis.shop.ui.account

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentProfileChangerBinding
import com.alexis.shop.ui.account.profile.ProfileViewModel
import com.alexis.shop.utils.*
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderEffectBlur
import kotlinx.android.synthetic.main.fragment_profile_changer.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ProfileChangerFragment : BaseFragment<FragmentProfileChangerBinding>() {
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel : ProfileViewModel by viewModels()
    override fun getViewBinding() =  FragmentProfileChangerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun main() {
        super.main()

        binding.edSatu.setText(param2)
        blurView()
        binding.btnBack.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.btnCancel.setOnClickListener {
            justOut()
        }

        binding.infoEditingFragment.text = "We'll send you an email asking you to confirm the change. " +
                "Click the link in the email to confirm that the email is yours."

        binding.edSatu.setTextAppearance(requireContext(), R.style.regularText)
        binding.edDua.setTextAppearance(requireContext(), R.style.regularText)
        binding.edTiga.setTextAppearance(requireContext(), R.style.regularText)
        binding.btnCheck.setOnClickListener {
            val telp = binding.edDua.text.toString().trim()
            val password = binding.edTiga.text.toString().trim()
            Log.d("TAGG", password)
            updatePhone(telp, password)
        }
    }

    private fun updatePhone(phone : String, password : String) {
        viewModel.updatePhone(phone, password).observe(viewLifecycleOwner){ response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            Toast.makeText(requireContext(), "Bserhasil Update", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun TextInputLayout.nonePasswordStyle() {
        endIconDrawable = null
        setHintTextAppearance(R.style.regularText)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun blurView() {
        val radius = 15f
        val decorView : View = activity?.window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderEffectBlur())
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileChangerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}