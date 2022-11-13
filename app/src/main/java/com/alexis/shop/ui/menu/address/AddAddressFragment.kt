package com.alexis.shop.ui.menu.address

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentAddAddressBinding
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.ui.checkout.SelectAddressFragment
import com.alexis.shop.ui.checkout.SelectAddressFragmentViewModel
import com.alexis.shop.ui.checkout.address.CityViewModel
import com.alexis.shop.utils.*
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


private const val ARG_whereFrom = "whereFrom"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class AddAddressFragment : BaseFragment<FragmentAddAddressBinding>() {

    override fun getViewBinding(): FragmentAddAddressBinding = FragmentAddAddressBinding.inflate(layoutInflater)
    private val cityViewModel : CityViewModel by viewModels()
    private val addressViewModel : SelectAddressFragmentViewModel by viewModels()
    private var whereFrom: Int? = null
    private var param2: String? = null

    private var listIdKecamatan = ArrayList<String>()
    private var listNameKecamatan = ArrayList<String>()
    lateinit var adapterkecamatan: ArrayAdapter<String>
    private var idkelurahanstring : String = ""


    lateinit var btn_cancel: ImageView
    lateinit var btn_back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()

        arguments?.let {
            whereFrom = it.getInt(ARG_whereFrom)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        super.main()
        blurView()
        var job : Job? = null
        val editText: AutoCompleteTextView = binding.kecSpin
        adapterkecamatan = ArrayAdapter<String>(
            requireContext(),
            R.layout.item_city, R.id.tv_nama_kel, listNameKecamatan
        )
        editText.setAdapter(adapterkecamatan)
        editText.addTextChangedListener{ editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        getCity(editable.toString())
                    }
                }
            }
        }

        showKelurahan()
        Log.d("IDEKJERE", idkelurahanstring)
        binding.btnCancel.setOnClickListener {
            justOut()
        }
        binding.btnBack.setOnClickListener {
            val fragment =
                //Back Button and select where it should be back
                when (whereFrom) {
                    SELECT_ADDRESS -> SelectAddressFragment()
                    CHANGE_ADDRESS -> ChangeAddressFragment.newInstance("", "")
                    else -> ChangeAddressFragment.newInstance("", "")
                }
            requireActivity().supportFragmentManager.shopNavigator(fragment)
        }
        binding.cbDefAdress.setOnCheckedChangeListener { _, _ ->
            requireActivity().hideSoftKeyboard()
        }

        binding.cbDrShiping.setOnCheckedChangeListener { _, _ ->
            requireActivity().hideSoftKeyboard()
        }

        binding.kecSpin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                (parent as TextView).setTextColor(Color.WHITE)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (view as TextView).setTextColor(Color.WHITE)
                if(position != 0){
                    idkelurahanstring = listIdKecamatan[position]
                    Log.d("IDEKJERE", idkelurahanstring)
                }
            }
        }
        binding.btnSubmit.setOnClickListener {
            if (
                binding.edAlias.text.toString() != "" &&
                binding.edRecname.text.toString() != "" &&
                binding.edAdress1.text.toString() != "" &&
                binding.edAdress2.text.toString() != "" &&
                binding.kecSpin.text.toString() != "" &&
                binding.edPocode.text.toString() != "" &&
                binding.edPhone.text.toString() != ""
             ) {

            }
        }
        val input = editText.text.toString()
        getCity(input)
    }

    private fun getCity(nama: String) {
        cityViewModel.getCitySearch(nama).observe(this) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val getCityValue = response.data?.data as List<CityItemModel>
                        handleKota(getCityValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            "Get City Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun postAddress(
        name : String,
        address : String,
        addressTwo : String
    ) {

    }

   private fun showKelurahan() {
       listIdKecamatan.add("");
       listNameKecamatan.add("Kecamatan");
       adapterkecamatan.notifyDataSetChanged()
       cityViewModel.mKotaState
           .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
           .onEach { kota ->
               handleKota(kota)
           }
           .launchIn(lifecycleScope)
   }
    private fun handleKota(kota : List<CityItemModel>){
        kota.map {
            listIdKecamatan.add(it.villageId)
            listNameKecamatan.add(it.fullName)
        }
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

//    lateinit var title_view: TextView
//
//    lateinit var text_alias: TextView
//    lateinit var text_recipient: TextView
//    lateinit var text_address1: TextView
//    lateinit var text_address2: TextView
//    lateinit var text_poscode: TextView
//    lateinit var text_phone: TextView
//    lateinit var txt_dropshipinfo: TextView
//    lateinit var dropdown_kecamatan: TextInputEditText
//    lateinit var lpw: ListPopupWindow
//    lateinit var cb_def_address: CheckBox
//    lateinit var cb_drop_shiping: CheckBox
//    lateinit var click_dropship: RelativeLayout
//
//    private var arrayKecamatan: ArrayList<String> = ArrayList()
}