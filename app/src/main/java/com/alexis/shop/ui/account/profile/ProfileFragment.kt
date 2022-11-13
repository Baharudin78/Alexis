package com.alexis.shop.ui.account.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexis.shop.R
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
class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var title: TextView
    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView

    lateinit var lclick_name: TextInputLayout
    lateinit var lclick_brdate: TextInputLayout

    lateinit var click_name: TextInputEditText
    lateinit var click_email: TextInputEditText
    lateinit var click_phone: TextInputEditText
    lateinit var click_password: TextInputEditText
    lateinit var click_brdate: TextInputEditText

    lateinit var click_verify: TextView

    lateinit var layoutedit_name: ConstraintLayout
    lateinit var layoutedit_brdate: ConstraintLayout
    lateinit var submit_name: ConstraintLayout
    lateinit var submit_birtdate: ConstraintLayout

    lateinit var et_day: EditText
    lateinit var et_month: EditText
    lateinit var et_year: EditText
    lateinit var ln_day: View
    lateinit var ln_month: View
    lateinit var ln_year: View

    lateinit var flashing_name: View
    lateinit var flashing_day: View
    lateinit var flashing_month: View
    lateinit var flashing_year: View

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        requireActivity().hideSoftKeyboard()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        title = root.findViewById(R.id.txt_wl)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)

        lclick_name = root.findViewById(R.id.lshow_name)
        lclick_brdate = root.findViewById(R.id.lshow_brdate)

        click_name = root.findViewById(R.id.show_name)
        click_email = root.findViewById(R.id.input_email)
        click_phone = root.findViewById(R.id.input_phone)
        click_password = root.findViewById(R.id.input_password)
        click_brdate = root.findViewById(R.id.show_brdate)

        click_verify = root.findViewById(R.id.click_verify)

        layoutedit_name = root.findViewById(R.id.editname_mode)
        layoutedit_brdate = root.findViewById(R.id.editbrdate_mode)

        submit_name = root.findViewById(R.id.sumbit_name)
        submit_birtdate = root.findViewById(R.id.sumbit_brdate)

        et_day = root.findViewById(R.id.brdate_day)
        et_month = root.findViewById(R.id.brdate_month)
        et_year = root.findViewById(R.id.brdate_year)
        ln_day = root.findViewById(R.id.line_day)
        ln_month = root.findViewById(R.id.line_month)
        ln_year = root.findViewById(R.id.line_year)

        flashing_name = root.findViewById(R.id.flashing_input_name)
        flashing_day = root.findViewById(R.id.flashing_day)
        flashing_month = root.findViewById(R.id.flashing_month)
        flashing_year = root.findViewById(R.id.flashing_year)

        return root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blurView()
        click_name.setOnClickListener {
            click_name.gone()
            lclick_name.gone()
            layoutedit_name.visible()

            click_brdate.visible()
            lclick_brdate.visible()
            layoutedit_brdate.gone()

            flashing_name.visible()
            withDelayTime(150){
                flashing_name.gone()
            }
        }
        click_email.setOnClickListener {
            val fragment = ProfileChangerFragment.newInstance("email", click_email.text.toString())
            requireActivity().supportFragmentManager.accountNavigator(fragment)
        }
        click_phone.setOnClickListener {
            val fragment = ProfileChangerFragment.newInstance("phone", click_phone.text.toString())
            requireActivity().supportFragmentManager.accountNavigator(fragment)
        }
        click_password.setOnClickListener {
            val fragment = ProfileChangerFragment.newInstance("password", "")
            requireActivity().supportFragmentManager.accountNavigator(fragment)
        }
        click_brdate.setOnClickListener {
            click_brdate.gone()
            lclick_brdate.gone()
            layoutedit_brdate.visible()

            click_name.visible()
            lclick_name.visible()
            layoutedit_name.gone()

            et_day.requestFocus()
            ln_day.visible()
            ln_month.gone()
            ln_year.gone()

            flashing_day.visible()
            withDelayTime(150){
                flashing_day.gone()
            }
        }
        submit_name.setOnClickListener {
            click_name.visible()
            lclick_name.visible()
            layoutedit_name.gone()
        }
        submit_birtdate.setOnClickListener {
            click_brdate.visible()
            lclick_brdate.visible()
            layoutedit_brdate.gone()
        }

        et_day.doOnTextChanged { text, start, before, count ->
            if(!text.isNullOrBlank()) {
                if (text.toString().toInt() >= 32) {
                    et_day.setText("31")
                }
            }
        }
        et_month.doOnTextChanged { text, start, before, count ->
            if(!text.isNullOrBlank()) {
                if (text.toString().toInt() >= 13) {
                    et_month.setText("12")
                }
            }
        }

        et_day.setOnFocusChangeListener { v, hasFocus ->
            ln_day.visible()
            ln_month.gone()
            ln_year.gone()

            if(hasFocus) {
                flashing_day.visible()
                withDelayTime(150) {
                    flashing_day.gone()
                }
            }
        }
        et_month.setOnFocusChangeListener { v, hasFocus ->
            ln_day.gone()
            ln_month.visible()
            ln_year.gone()

            if(hasFocus) {
                flashing_month.visible()
                withDelayTime(150) {
                    flashing_month.gone()
                }
            }
        }
        et_year.setOnFocusChangeListener { v, hasFocus ->
            ln_day.gone()
            ln_month.gone()
            ln_year.visible()

            if(hasFocus) {
                flashing_year.visible()
                withDelayTime(150) {
                    flashing_year.gone()
                }
            }
        }

        popUpOnBottom(requireContext(), "click here to verify").showAlignTop(click_verify)

        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        cancel_button.setOnClickListener {
            justOut()
        }

        getProfileData()
    }

    private fun getProfileData() {
        val profileData = viewModel.getProfileData()
//        click_name.setText(profileData.fullName)
//        click_email.setText(profileData.email)
//        click_phone.setText(profileData.phone)
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