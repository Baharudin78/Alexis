package com.alexis.shop.ui.account.register

import android.app.Activity
import android.os.Build
import android.text.Html
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.databinding.FragmentRegisterBinding
import com.alexis.shop.ui.account.login.LoginFragment
import com.alexis.shop.utils.*
import com.alexis.shop.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun getViewBinding() = FragmentRegisterBinding.inflate(layoutInflater)

    override fun main() {
        val textTerms = "<a>I agree to connect my account in accordance with the" +
                "<font color=\"#FFFFFF\"> <strong>Terms and Conditions</strong> </font>" +
                "and <font color=\"#FFFFFF\"><strong>Privacy Policy</strong> </font> </a>"

        with(binding) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cbTerms.text = Html.fromHtml(textTerms, Html.FROM_HTML_MODE_COMPACT)
            } else {
                cbTerms.text = Html.fromHtml(textTerms)
            }

            edName.requestFocus()
            val imm: InputMethodManager = root.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

            btnCancel.setOnClickListener { justOut() }
            loginButton.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
            buttonRegister.setOnClickListener { validateRegistration() }
        }
    }

    private fun validateRegistration() {
        //Vibrate Animation
        val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.animate_shake_set)

        with(binding) {
            when {
                !AuthValidationHelper.isFormValid(edName.text.toString()) -> {
                    inputName.startAnimation(shake)
                    edName.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isPhoneValid(edPhone.text.toString()) -> {
                    inputPhone.startAnimation(shake)
                    edPhone.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isEmailValid(edEmail.text.toString()) -> {
                    inputEmail.startAnimation(shake)
                    edEmail.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isPasswordValid(edPassword.text.toString()) -> {
                    inputPassword.startAnimation(shake)
                    edPassword.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isPasswordValid(edPassword2.text.toString()) -> {
                    inputPassword2.startAnimation(shake)
                    edPassword2.errorSignEditText(viewLifecycleOwner)
                }
                edPassword.text.toString() != edPassword2.text.toString() -> {
                    inputPassword2.startAnimation(shake)
                    edPassword2.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isValidDate(edTanggal.text.toString()) ->{
                    inputTanggal.startAnimation(shake)
                    edTanggal.errorSignEditText(viewLifecycleOwner)
                }
                !cbTerms.isChecked -> {
                    cbTerms.startAnimation(shake)
                    popUpOnBottom(requireContext(), "check this agreement first").showAlignBottom(
                        cbTerms
                    )
                }
                else -> doRegister(
                    edName.text.toString(),
                    edPhone.text.toString(),
                    edEmail.text.toString(),
                    edPassword.text.toString(),
                    edPassword2.text.toString(),
                    edTanggal.text.toString()
                )
            }
        }
    }

    private fun doRegister(name: String, phone: String, email: String, password: String,confirmPassword : String, tanggalLahir : String) {
        changeButtonRegisterStatus(false)
        viewModel.register(name, phone, email, password,confirmPassword, tanggalLahir).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        changeButtonRegisterStatus(true)
                        Toast.makeText(binding.root.context, "Register Success", Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.menuNavigator(LoginFragment())
                    }
                    is Resource.Error -> {
                        changeButtonRegisterStatus(true)
                        Toast.makeText(binding.root.context, getString(R.string.auth_error, "Register"), Toast.LENGTH_SHORT).show()
                        //requireActivity().supportFragmentManager.menuNavigator(LoginFragment())

                    }
                }
            }
        }
    }

    private fun changeButtonRegisterStatus(state: Boolean) {
        with(binding.buttonRegister) {
            isEnabled = state
            isClickable = state
        }
    }
}