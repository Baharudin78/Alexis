package com.alexis.shop.ui.shopping_bag

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.alexis.shop.R
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.invisible
import com.alexis.shop.utils.visible

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditShoppingBagFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var ok_btn: ConstraintLayout
    lateinit var add_btn: ConstraintLayout
    lateinit var back_btn: ImageView
    lateinit var editlay: LinearLayout

    lateinit var tv_xs: TextView
    lateinit var tv_s: TextView
    lateinit var tv_m: TextView
    lateinit var tv_l: TextView
    lateinit var tv_xl: TextView

    lateinit var ed_xs: EditText
    lateinit var ed_s: EditText
    lateinit var ed_m: EditText
    lateinit var ed_l: EditText
    lateinit var ed_xl: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        handleBackPressed()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_edit_shopping_bag, container, false)
        ok_btn = root.findViewById(R.id.ok_btn)
        add_btn = root.findViewById(R.id.add_btn)
        back_btn = root.findViewById(R.id.btn_cancel2)
        editlay = root.findViewById(R.id.size_et)

        tv_xs = root.findViewById(R.id.tv_xs)
        tv_s = root.findViewById(R.id.tv_s)
        tv_m = root.findViewById(R.id.tv_m)
        tv_l = root.findViewById(R.id.tv_l)
        tv_xl = root.findViewById(R.id.tv_xl)

        tv_xs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        tv_s.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        tv_m.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        tv_l.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        tv_xl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)

        ed_xs = root.findViewById(R.id.ed_xs)
        ed_s = root.findViewById(R.id.ed_s)
        ed_m = root.findViewById(R.id.ed_m)
        ed_l = root.findViewById(R.id.ed_l)
        ed_xl = root.findViewById(R.id.ed_xl)

        return root
    }

    private var modeEdit: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener {
            modeEdit = true
            editlay.visible()
            add_btn.invisible()
        }

        ok_btn.setOnClickListener {
            if(modeEdit) {
                modeEdit = false
                editlay.invisible()
                add_btn.visible()
            } else {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

        back_btn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        changeCoosenSize(ed_xs, tv_xs)
        changeCoosenSize(ed_s, tv_s)
        changeCoosenSize(ed_m, tv_m)
        changeCoosenSize(ed_l, tv_l)
        changeCoosenSize(ed_xl, tv_xl)
    }

    fun changeCoosenSize(ed: EditText, tv: TextView){
        ed.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && ed.text.toString() == "0") ed.setText("")
        }
        ed.doOnTextChanged { text, _, _, _ ->
            val num = when(text.toString()){
                "" -> 0
                else -> text.toString().toInt()
            }
            when(num){
                0 -> {
                    tv.setTypeface(null, Typeface.NORMAL)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextColor(
                            requireContext().resources
                                .getColor(R.color.white, requireContext().theme)
                        )
                    } else {
                        tv.setTextColor(
                            requireContext().resources.getColor(R.color.white)
                        )
                    }
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                }
                else -> {
                    tv.setTypeface(tv.typeface, Typeface.BOLD)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextColor(
                            requireContext().resources
                                .getColor(R.color.alexis_orange, requireContext().theme)
                        )
                    } else {
                        tv.setTextColor(
                            requireContext().resources.getColor(R.color.alexis_orange)
                        )
                    }
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                EditShoppingBagFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}