package com.alexis.shop.ui.account.login

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.databinding.FragmentLoginBinding
import com.alexis.shop.ui.account.register.RegisterFragment
import com.alexis.shop.ui.menu.MenuFragment
import com.alexis.shop.utils.*
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)

    override fun main() {
        handleBackPressed()
        with(binding) {
            edEmail.requestFocus()
            val imm: InputMethodManager =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

            Animations.runAnimation(
                requireActivity(),
                Animations.ANIMATION_IN,
                null,
                this.btnRegister,
                this.viewBtnLoginWith,
                this.inputPassword,
                this.inputEmail
            )
            setListener()
        }
    }

    private fun setListener() {
        with(binding) {
            btnCancel.setOnClickListener {
                Animations.runAnimation(
                    requireActivity(),
                    Animations.ANIMATION_OUT,
                    null,
                    this.btnRegister,
                    this.viewBtnLoginWith,
                    this.inputPassword,
                    this.inputEmail
                )

                Handler(Looper.myLooper()!!).postDelayed({
                    justOut()
                }, 1000L)
            }
            loginButton.setOnClickListener { validateLogin() }
            btnRegister.setOnClickListener {
                requireActivity().supportFragmentManager.accountNavigator(
                    RegisterFragment()
                )
            }
            edPassword.setOnClickListener {
                edPassword.passwordIsVisible(true)
            }
        }
    }

    private fun validateLogin() {
        //Animation when field is empty or wrong
        val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.animate_shake_set)
        with(binding) {
            when {
                !AuthValidationHelper.isFormValid(edEmail.text.toString()) -> {
                    inputEmail.startAnimation(shake)
                    edEmail.errorSignEditText(viewLifecycleOwner)
                }
                !AuthValidationHelper.isPasswordValid(edPassword.text.toString()) -> {
                    inputPassword.startAnimation(shake)
                    edPassword.errorSignEditText(viewLifecycleOwner)
                }
                else -> doLogin(edEmail.text.toString(), edPassword.text.toString())
            }
        }
    }

    private fun doLogin(email: String, password: String) {
        changeButtonLoginStatus(false)
        viewModel.login(email, password).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            viewModel.saveLoginCredential(it)
                            requireActivity().supportFragmentManager.menuNavigator(
                                MenuFragment.newInstance("", "222")
                            )
                        }
                        changeButtonLoginStatus(true)
                    }
                    is Resource.Error -> {
                        changeButtonLoginStatus(true)
                        Toast.makeText(binding.root.context, getString(R.string.auth_error, "Login"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun changeButtonLoginStatus(state: Boolean) {
        with(binding.loginButton) {
            isEnabled = state
            isClickable = state
        }
    }
}