package com.alexis.shop.ui.menu.address

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivityAddAddressBinding
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.ui.checkout.SelectAddressFragmentViewModel
import com.alexis.shop.ui.checkout.address.CityActivity
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.hideSoftKeyboard
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class AddAddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddAddressBinding
    private val viewModel : SelectAddressFragmentViewModel by viewModels()
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    var idKecamatan : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        blurView()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        setListener()
        submitAddress()
    }

    private fun setListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.cbDefAdress.setOnCheckedChangeListener{_, _ ->
            this.hideSoftKeyboard()
        }
        binding.cbDrShiping.setOnCheckedChangeListener{_,_ ->
            this.hideSoftKeyboard()
        }

        binding.etKec.setOnClickListener {
            val intent = Intent(applicationContext, CityActivity::class.java)
            startActivityForResult(intent, 100)
        }
        binding.etLtlon.setOnClickListener {
            getLocation()
        }

    }

    private fun submitAddress() {
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
        viewModel.postCheckoutAddress(
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
        ).observe(this) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        finish()
                        Toast.makeText(applicationContext, "Berhasill", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            applicationContext,
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
        val decorView : View = window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(applicationContext))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
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
            (data.extras?.get(AddAddressFragment.ADDRESS) as CityItemModel?)?.let { it ->
                idKecamatan = it.villageId
                Log.d("NAMASASAS", idKecamatan)
                binding.etKec.setText(it.fullName)
                binding.kecamatan.text = it.villageId
                Log.d("NAMASASAS", it.fullName)
            }
        }
    }
}