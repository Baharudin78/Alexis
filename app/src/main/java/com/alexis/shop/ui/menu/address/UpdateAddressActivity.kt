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
import com.alexis.shop.domain.model.address.AddressItemModel
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.ui.checkout.SelectAddressFragmentViewModel
import com.alexis.shop.ui.checkout.address.CityActivity
import com.alexis.shop.utils.hideSoftKeyboard
import com.alexis.shop.utils.orZero
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur


@AndroidEntryPoint
class UpdateAddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddAddressBinding
    private val viewModel : SelectAddressFragmentViewModel by viewModels()
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    var idKecamatan : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)

        val data = intent.getParcelableExtra<AddressItemModel>(AddAddressActivity.ADDRESS_DATA)
        Log.d("ADRESSSS", "$data")
        val idAddress = data?.id.orZero()
        val nama = binding.edRecname.setText(data?.recipientName.orEmpty())
        val address1 = binding.edAdress1.setText(data?.address.orEmpty())
        val address2 = binding.edAdress2.setText(data?.addressTwo.orEmpty())
        val vilageId = binding.kecamatan.setText(data?.villageId.orEmpty())
        val kodePos = binding.edPocode.setText(data?.postalCode.orEmpty())
        val lat = binding.etLtlon.setText(data?.latitude.orEmpty())
        val long = binding.etLtlon.setText(data?.longitude.orEmpty())
        val phone = binding.edPhone.setText(data?.recipientPhoneNumber.orEmpty())
        binding.btnSubmit.setOnClickListener {
            var isDrop = 0
            var isDefault = 0
            val name = nama.toString()
            val addressOne = address1.toString()
            val addressTwo = address2.toString()
            val kecId = vilageId.toString()
            val kodePost = kodePos.toString()
            val latitude = lat.toString()
            val longitude = long.toString()
            val noHp = phone.toString()
            isDrop = if (binding.cbDrShiping.isChecked) {
                1
            }else{
                0
            }
            isDefault = if (binding.cbDefAdress.isChecked) {
                1
            }else{
                0
            }
            if (validateField(name, addressOne,addressTwo, kecId, kodePost, latitude, longitude, noHp, isDrop, isDefault)) {
                updateAddressss(
                    idAddress,
                    name,
                    addressOne,
                    addressTwo,
                    kecId,
                    kodePost,
                    latitude,
                    longitude,
                    noHp,
                    isDrop,
                    isDefault
                )
            }

        }
        blurView()
        setListener()
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

    private fun updateAddressss(
        id : Int,
        name : String,
        address : String,
        addressTwo : String,
        idKecamatan : String,
        kodePos : String,
        phone : String,
        latitude : String,
        longitude : String,
        isDefault : Int,
        isDropship : Int,
        ) {
        viewModel.updateAddress(
            id,
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
            (data.extras?.get(AddAddressActivity.ADDRESS_ID) as CityItemModel?)?.let { it ->
                idKecamatan = it.villageId
                Log.d("NAMASASAS", idKecamatan)
                binding.etKec.setText(it.fullName)
                binding.kecamatan.text = it.villageId
                Log.d("NAMASASAS", it.fullName)
            }
        }
    }

    companion object{
        const val ADDRESS_ID = "ADDRESS_ID"
        const val ADDRESS_DATA = "ADDRESS_DATA"
    }
}