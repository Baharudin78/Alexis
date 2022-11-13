package com.alexis.shop.ui.account

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.alexis.shop.R
import com.alexis.shop.utils.*
import com.alexis.shop.utils.common.withDelayTime
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_profile_changer.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileChangerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView

    lateinit var layoutinput1: TextInputLayout
    lateinit var layoutinput2: TextInputLayout
    lateinit var layoutinput3: TextInputLayout
    lateinit var edittext1: TextInputEditText
    lateinit var edittext2: TextInputEditText
    lateinit var edittext3: TextInputEditText
    lateinit var info_input: TextView
    lateinit var btn_check: ImageView

    lateinit var line1: View
    lateinit var line2: View
    lateinit var line3: View

    lateinit var flashing1: View
    lateinit var flashing2: View
    lateinit var flashing3: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_profile_changer, container, false)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)

        layoutinput1 = root.findViewById(R.id.input_satu)
        layoutinput2 = root.findViewById(R.id.input_dua)
        layoutinput3 = root.findViewById(R.id.input_tiga)
        edittext1 = root.findViewById(R.id.ed_satu)
        edittext2 = root.findViewById(R.id.ed_dua)
        edittext3 = root.findViewById(R.id.ed_tiga)
        info_input = root.findViewById(R.id.info_editing_fragment)
        line1 = root.findViewById(R.id.line_satu)
        line2 = root.findViewById(R.id.line_dua)
        line3 = root.findViewById(R.id.line_tiga)
        flashing1 = root.findViewById(R.id.flashing_satu)
        flashing2 = root.findViewById(R.id.flashing_dua)
        flashing3 = root.findViewById(R.id.flashing_tiga)
        btn_check = root.findViewById(R.id.btn_check)

        return root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blurView()
        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        cancel_button.setOnClickListener {
            justOut()
        }

        edittext1.setTextAppearance(requireContext(), R.style.regularText)
        edittext2.setTextAppearance(requireContext(), R.style.regularText)
        edittext3.setTextAppearance(requireContext(), R.style.regularText)

        when {
            param1.equals("email") -> {
                layoutinput1.hint = "Old email"
                layoutinput2.hint = "New email"
                layoutinput3.hint = "Password"

                layoutinput1.hint = "Old email"
                edittext1.setText(param2)
                layoutinput2.hint = "New email"
                layoutinput3.hint = "Password"

                edittext1.setReadOnly(true)

                layoutinput1.nonePasswordStyle()
                layoutinput2.nonePasswordStyle()

                edittext1.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                edittext2.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

                info_input.text = "We'll send you an email asking you to confirm the change. " +
                        "Click the link in the email to confirm that the email is yours."

                edittext2.requestFocus()
                flashing2.visible()
                withDelayTime(150) {
                    flashing2.gone()
                }

                line1.gone()
                line2.visible()
                line3.gone()
            }
            param1.equals("phone") -> {
                layoutinput1.hint = "Old Phone"
                layoutinput2.hint = "New Phone"
                layoutinput3.hint = "Password"

                layoutinput1.hint = "Old Phone"
                edittext1.setText("+62 811 3213 5445")
                layoutinput2.hint = "New Phone"
                edittext2.setText("+62")
                layoutinput3.hint = "Password"

                edittext1.setReadOnly(true)

                layoutinput1.nonePasswordStyle()
                layoutinput2.nonePasswordStyle()

                edittext1.inputType = InputType.TYPE_CLASS_NUMBER
                edittext2.inputType =  InputType.TYPE_CLASS_NUMBER

                info_input.text = "We'll send you a SMS asking you to confirm the change. " +
                        "Click the link in the SMS to confirm that the number is yours."

                edittext2.requestFocus()
                flashing2.visible()
                withDelayTime(150){
                    flashing2.gone()
                }

                line1.gone()
                line2.visible()
                line3.gone()
            }
            else -> {

                btn_check.setImageResource(R.drawable.ic_check)

                layoutinput1.hint = "Old password"
                layoutinput2.hint = "New password"
                layoutinput3.hint = "Reenter new password"

                layoutinput1.hint = "Old password"
                layoutinput2.hint = "New password"
                layoutinput3.hint = "Reenter new password"

                edittext1.requestFocus()
                flashing1.visible()
                withDelayTime(150){
                    flashing1.gone()
                }

                line1.visible()
                line2.gone()
                line3.gone()
            }
        }

        //Show Keyboard
        val imm: InputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        edittext1.setOnFocusChangeListener { _, hasFocus ->
            line1.visible()
            line2.gone()
            line3.gone()

            if(hasFocus) {
                flashing1.visible()
                withDelayTime(150) {
                    flashing1.gone()
                }
            }
        }
        edittext2.setOnFocusChangeListener { _, hasFocus ->
            line1.gone()
            line2.visible()
            line3.gone()

            if(hasFocus) {
                flashing2.visible()
                withDelayTime(150) {
                    flashing2.gone()
                }
            }
        }
        edittext3.setOnFocusChangeListener { _, hasFocus ->
            line1.gone()
            line2.gone()
            line3.visible()

            if(hasFocus) {
                flashing3.visible()
                withDelayTime(150) {
                    flashing3.gone()
                }
            }
        }
    }

    private fun TextInputLayout.nonePasswordStyle() {
        endIconDrawable = null
        setHintTextAppearance(R.style.regularText)
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
                ProfileChangerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}