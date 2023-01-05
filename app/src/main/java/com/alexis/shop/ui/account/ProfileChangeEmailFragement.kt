package com.alexis.shop.ui.account

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentProfileChangeEmailBinding
import com.alexis.shop.ui.account.profile.ProfileViewModel
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderEffectBlur


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ProfileChangeEmailFragement : BaseFragment<FragmentProfileChangeEmailBinding>() {
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel : ProfileViewModel by viewModels()

    override fun getViewBinding() = FragmentProfileChangeEmailBinding.inflate(layoutInflater)

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

        blurView()
        binding.edSatu.setText(param2)
        binding.btnBack.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.btnCancel.setOnClickListener {
            justOut()
        }

        binding.edSatu.setTextAppearance(requireContext(), R.style.regularText)
        binding.edDua.setTextAppearance(requireContext(), R.style.regularText)
        binding.edTiga.setTextAppearance(requireContext(), R.style.regularText)
        binding.btnCheck.setOnClickListener {
            val email = binding.edDua.text.toString().trim()
            val password = binding.edTiga.text.toString().trim()
            Log.d("TAGG", password)
            updateEmail(email, password)
        }
    }

    private fun updateEmail(email : String, password : String) {
        viewModel.updateEmail(email, password).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            Toast.makeText(requireContext(), "Berhasil update Email", Toast.LENGTH_SHORT).show()
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

    companion object{
        @JvmStatic
        fun newInstance(param1 : String, param2 : String) =
            ProfileChangeEmailFragement().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
