package com.alexis.shop.ui.account.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentProfileBinding
import com.alexis.shop.domain.model.profil.ProfilModel
import com.alexis.shop.ui.account.ProfileChangerFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelayTime
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_profile.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: ProfileViewModel by viewModels()
    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)
    private var profilModel : ProfilModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        requireActivity().hideSoftKeyboard()
    }

    override fun main() {
        super.main()
        popUpOnBottom(requireContext(), "click here to verify").showAlignTop(click_verify)

        blurView()
        with(binding) {
            showName.setOnClickListener {
                showName.gone()
                lshowName.gone()
                editnameMode.visible()
                showBrdate.visible()
                lshowBrdate.visible()
                editbrdateMode.gone()
                flashingInputName.visible()
                withDelayTime(150) {
                    flashingInputName.gone()
                }
            }
            inputEmail.setOnClickListener {
                val fragment = ProfileChangerFragment.newInstance("email", inputEmail.text.toString())
                requireActivity().supportFragmentManager.accountNavigator(fragment)
            }
            inputPhone.setOnClickListener {
                val fragment = ProfileChangerFragment.newInstance("phone", inputPhone.text.toString())
                requireActivity().supportFragmentManager.accountNavigator(fragment)
            }
            inputPassword.setOnClickListener {
                val fragment = ProfileChangerFragment.newInstance("password", "")
                requireActivity().supportFragmentManager.accountNavigator(fragment)
            }

            showBrdate.setOnClickListener {
                showBrdate.gone()
                lshowBrdate.gone()
                editbrdateMode.visible()
                showName.visible()
                lshowName.visible()
                editnameMode.gone()

                brdateDay.requestFocus()
                lineDay.visible()
                lineMonth.gone()
                lineYear.gone()
                flashingDay.visible()
                withDelayTime(150) {
                    flashingDay.gone()
                }
            }
            sumbitName.setOnClickListener {
                showName.visible()
                lshowName.visible()
                editnameMode.gone()
                val nama = binding.edName.text.toString().trim()
                updateName(nama)
            }
            sumbitBrdate.setOnClickListener {
                showBrdate.visible()
                lshowBrdate.visible()
                editbrdateMode.gone()
            }

            brdateDay.doOnTextChanged{text, start, before, count ->
                if(!text.isNullOrBlank()) {
                    if (text.toString().toInt() >= 32) {
                        brdateDay.setText("31")
                    }
                }
            }
            brdateMonth.doOnTextChanged{text, start, before, count ->
                if(!text.isNullOrBlank()) {
                    if (text.toString().toInt() >= 13) {
                        brdateMonth.setText("12")
                    }
                }
            }
            brdateDay.setOnFocusChangeListener { v, hasFocus ->
                lineDay.visible()
                lineMonth.gone()
                lineYear.gone()
                if (hasFocus) {
                    flashingDay.visible()
                    withDelayTime(150) {
                        flashingDay.gone()
                    }
                }
            }
            brdateMonth.setOnFocusChangeListener {v, hasFocus ->
                lineDay.gone()
                lineMonth.visible()
                lineYear.gone()
                if (hasFocus) {
                    flashingMonth.visible()
                    withDelayTime(150) {
                        flashingMonth.gone()
                    }
                }
            }
            brdateYear.setOnFocusChangeListener {v, hasFocus ->
                lineDay.gone()
                lineMonth.gone()
                lineYear.visible()
                if (hasFocus) {
                    flashingYear.visible()
                    withDelayTime(150) {
                        flashingYear.gone()
                    }
                }
            }
            btnBack.setOnClickListener{
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnCancel.setOnClickListener{
                justOut()
            }

        }
        getProfileData()
    }

    private fun getProfileData() {
        viewModel.getProfileData().observe(viewLifecycleOwner) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            profilModel = it
                            setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Failed Get Profil",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun updateName(name :String){
        viewModel.updateName(name).observe(viewLifecycleOwner){response ->
            if (response != null) {
                when(response){
                    is Resource.Loading ->{}
                    is Resource.Success ->{
                        response.data?.let {
                            profilModel= it
                           setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Failed Get Profil",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun updatePhone(phone :String){
        viewModel.updatePhone(phone).observe(viewLifecycleOwner){response ->
            if (response != null) {
                when(response){
                    is Resource.Loading ->{}
                    is Resource.Success ->{
                        response.data?.let {
                            profilModel= it
                            setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Failed Get Profil",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupView(data : ProfilModel) {
        binding.showName.setText(data.nama_lengkap.toString())
        binding.inputEmail.setText(data.email.toString())
        binding.inputPhone.setText(data.no_telp.toString())
        binding.inputPassword.setText("********")
        binding.showBrdate.setText(data.tanggal_lahir.toString())
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
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}