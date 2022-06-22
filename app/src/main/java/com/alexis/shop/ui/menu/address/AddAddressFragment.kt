package com.alexis.shop.ui.menu.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.alexis.shop.R
import com.alexis.shop.ui.checkout.SelectAddressFragment
import com.alexis.shop.utils.*
import com.google.android.material.textfield.TextInputEditText


private const val ARG_whereFrom = "whereFrom"
private const val ARG_PARAM2 = "param2"

class AddAddressFragment : Fragment() {
    private var whereFrom: Int? = null
    private var param2: String? = null

    lateinit var btn_cancel: ImageView
    lateinit var btn_back: ImageView
    lateinit var title_view: TextView

    lateinit var text_alias: TextView
    lateinit var text_recipient: TextView
    lateinit var text_address1: TextView
    lateinit var text_address2: TextView
    lateinit var text_poscode: TextView
    lateinit var text_phone: TextView
    lateinit var txt_dropshipinfo: TextView
    lateinit var dropdown_kecamatan: TextInputEditText
    lateinit var lpw: ListPopupWindow
    lateinit var cb_def_address: CheckBox
    lateinit var cb_drop_shiping: CheckBox
    lateinit var click_dropship: RelativeLayout

    private var arrayKecamatan: ArrayList<String> = ArrayList()
    lateinit var adapterKecamatan: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            whereFrom = it.getInt(ARG_whereFrom)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root    = inflater.inflate(R.layout.fragment_add_address, container, false)
        btn_cancel          = root.findViewById(R.id.btn_cancel)
        btn_back            = root.findViewById(R.id.btn_back)
        title_view          = root.findViewById(R.id.txt_wl)
        text_alias          = root.findViewById(R.id.ed_alias)
        text_recipient      = root.findViewById(R.id.ed_recname)
        text_address1       = root.findViewById(R.id.ed_adress1)
        text_address2       = root.findViewById(R.id.ed_adress2)
        text_poscode        = root.findViewById(R.id.ed_pocode)
        text_phone          = root.findViewById(R.id.ed_phone)
        txt_dropshipinfo    = root.findViewById(R.id.txtInfo)
        dropdown_kecamatan  = root.findViewById(R.id.ed_kecamatan)
        cb_def_address      = root.findViewById(R.id.cb_defAdress)
        cb_drop_shiping     = root.findViewById(R.id.cb_drShiping)
        click_dropship      = root.findViewById(R.id.ds_click)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(whereFrom == SELECT_ADDRESS){
            title_view.text = "Input New Address"
            txt_dropshipinfo.invisible()
            click_dropship.setOnClickListener {
                //Show black text information popUp
                popUpOnBottom(requireContext(), getString(R.string.en_infodropshiping),
                        20, 130).showAlignBottom(it)
            }
        }
        
        btn_cancel.setOnClickListener {
            justOut()
        }
        btn_back.setOnClickListener {
            val fragment =
                //Back Button and select where it should be back
                when (whereFrom) {
                    SELECT_ADDRESS -> SelectAddressFragment()
                    CHANGE_ADDRESS -> ChangeAddressFragment.newInstance("", "")
                    else -> ChangeAddressFragment.newInstance("", "")
                }
            requireActivity().supportFragmentManager.shopNavigator(fragment)
        }

        setUpDropdownKecamatan()

        cb_def_address.setOnCheckedChangeListener { _, _ ->
            requireActivity().hideSoftKeyboard()
        }

        cb_drop_shiping.setOnCheckedChangeListener { _, _ ->
            requireActivity().hideSoftKeyboard()
        }
    }

    private fun setUpDropdownKecamatan(){
        dropdown_kecamatan.setOnTouchListener { _, _ ->
            lpw.show()
            true
        }

        lpw = ListPopupWindow(requireContext())

        //Add some sample
        arrayKecamatan.add("Grogol")
        arrayKecamatan.add("Kalideres")
        arrayKecamatan.add("Kembangan")
        arrayKecamatan.add("Kebon Jeruk")
        arrayKecamatan.add("Palmerah")

        //Set Adapter Dropdown
        adapterKecamatan = ArrayAdapter(requireContext(), R.layout.item_dropdown, arrayKecamatan)
        lpw.setAdapter(adapterKecamatan)
        lpw.anchorView = dropdown_kecamatan
        lpw.isModal = true
        lpw.setOnItemClickListener { _, _, position, _ ->
            val item: String = arrayKecamatan[position]
            dropdown_kecamatan.setText(item)
            lpw.dismiss()
        }
    }

    companion object {
        val SELECT_ADDRESS = 1
        val CHANGE_ADDRESS = 2

        @JvmStatic
        fun newInstance(whereFrom: Int, param2: String) =
            AddAddressFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_whereFrom, whereFrom)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}