package com.alexis.shop.ui.menu.address

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentAddAddressBinding
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.ui.checkout.SelectAddressFragment
import com.alexis.shop.ui.checkout.SelectAddressFragmentViewModel
import com.alexis.shop.ui.checkout.address.CityActivity
import com.alexis.shop.ui.checkout.address.CityViewModel
import com.alexis.shop.utils.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur


private const val ARG_whereFrom = "whereFrom"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class AddAddressFragment : BaseFragment<FragmentAddAddressBinding>() {

    override fun getViewBinding(): FragmentAddAddressBinding = FragmentAddAddressBinding.inflate(layoutInflater)
    private val addressViewModel : SelectAddressFragmentViewModel by viewModels()
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private var whereFrom: Int? = null
    private var param2: String? = null

    var idKecamatan : String =""


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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

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
        binding.etKec.setOnClickListener{
            val intent = Intent(requireContext(), CityActivity::class.java)
            startActivityForResult(intent, 100)
        }
        binding.etLtlon.setOnClickListener{
            getLocation()
        }
       initListener()

    }

    private fun initListener() {
        postAddress()
    }

    private fun postAddress() {
        if (binding.cbDrShiping.isChecked) {

        }
        binding.btnSubmit.setOnClickListener {
            val isDropShiped  = 1
            val isDefaults = 1
            val nama = binding.edRecname.text.toString().trim()
            val address = binding.edAdress1.text.toString().trim()
            val addressTwo = binding.edAdress2.text.toString().trim()
            val idKecamatan = binding.kecamatan.text.toString().trim()
            val kodePos = binding.edPocode.text.toString().trim()
            val latitude = binding.etLtlon.text.toString().trim()
            val longitude = binding.etLtlon.text.toString().trim()
            val noHp = binding.edPhone.text.toString().trim()
            val isDefault = isDefaults
            val isDrop = isDropShiped
//            if (binding.cbDrShiping.isChecked) {
//                isDrop = 1.toString()
//            }
//            if (binding.cbDefAdress.isChecked) {
//                isDefault = 1.toString()
//            }
            if (validateField(nama, address, addressTwo, idKecamatan, kodePos, latitude, longitude, noHp, isDefault, isDrop)) {
               postAddressss(
                   nama,
                   address,
                   addressTwo,
                   idKecamatan,
                   kodePos,
                   noHp,
                   isDefault,
                   isDrop,
                   latitude,
                   longitude,
               )
            }
        }
    }

    private fun validateField(
        name : String,
        address : String,
        addressTwo : String,
        idKecamatan : String,
        kodePos : String,
        latitude : String,
        longitude : String,
        phone : String,
        isDefault : Int,
        isDropship : Int
    ) : Boolean{
        resetAllError()
        if (name.isEmpty()) {
            namaError("Masukkan nama ")
            return false
        }
        if (address.isEmpty()) {
            addressError("Masukkan  Alamat")
            return false
        }

        if (addressTwo.isEmpty()) {
            addressTwoError("Masukkan Alamat")
            return false
        }

        if (idKecamatan.isEmpty()) {
            idKecamatanError("Masukkan Kecamatan")
            return false
        }

        if (kodePos.isEmpty()) {
            kodePosError("Masukkan kode pos")
            return false
        }

        if (latitude.isEmpty()) {
            latitudeError("Masukkan latitude")
            return false
        }

        if (longitude.isEmpty()) {
            longitudeError("Masukkan longitude")
            return false
        }

        if (phone.isEmpty()) {
            phoneError("Masukkan hp")
            return false
        }
        return true
    }

    private fun resetAllError() {
        namaError(null)
        addressError(null)
        addressTwoError(null)
        idKecamatanError(null)
        kodePosError(null)
        latitudeError(null)
        longitudeError(null)
        phoneError(null)
        isDefaultError(null)
        isDropError(null)
    }



    private fun namaError(e : String?) {
        binding.edRecname.error = e
    }
    private fun addressError(e : String?) {
        binding.edRecname.error = e
    }
    private fun addressTwoError(e : String?) {
        binding.edRecname.error = e
    }
    private fun idKecamatanError(e : String?) {
        binding.kecamatan.error = e
    }
    private fun kodePosError(e : String?) {
        binding.edPocode.error = e
    }
    private fun latitudeError(e : String?) {
        binding.etLtlon.error = e
    }
    private fun longitudeError(e : String?) {
        binding.etLtlon.error = e
    }
    private fun phoneError(e : String?) {
        binding.edPhone.error = e
    }
    private fun isDefaultError(e : String?) {
        binding.cbDefAdress.error = e
    }
    private fun isDropError(e : String?) {
        binding.cbDrShiping.error = e
    }

    private fun postAddressss(
        name : String,
        address : String,
        addressTwo : String,
        idKecamatan : String,
        kodePos : String,
        phone : String,
        isDefault : Int,
        isDropship : Int,
        latitude : String,
        longitude : String,

    ) {
        addressViewModel.postCheckoutAddress(
            name,
            address,
            addressTwo,
            idKecamatan,
            kodePos,
            phone,
            isDefault,
            isDropship,
            latitude,
            longitude,
        ).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                       handleBackPressed()
                        Toast.makeText(requireContext(), "Berhasill", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
            return
        }
        val location = fusedLocationClient.lastLocation
        location.addOnSuccessListener { locations ->
            if (locations != null) {
                val latitude = locations.latitude.toString()
                val longitude = locations.longitude.toString()
                binding.etLtlon.setText("$latitude $longitude")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null) {
            (data.extras?.get(ADDRESS) as CityItemModel?)?.let { it ->
                idKecamatan = it.villageId
                Log.d("NAMASASAS", idKecamatan)
                binding.etKec.setText(it.fullName)
                binding.kecamatan.text = it.villageId
                Log.d("NAMASASAS", it.fullName)
            }
        }
    }
    companion object {
        val SELECT_ADDRESS = 1
        val CHANGE_ADDRESS = 2
        val ADDRESS ="ADDRESS"

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