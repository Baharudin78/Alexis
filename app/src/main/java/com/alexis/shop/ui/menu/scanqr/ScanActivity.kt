package com.alexis.shop.ui.menu.scanqr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.ActivityScanBinding
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.ui.detail.ExpanItemPagersActivity
import com.alexis.shop.ui.detail.frombarcode.ExpandItemPagerTwoActivity
import com.alexis.shop.ui.menu.MenuFragment
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityScanBinding
    private lateinit var codeScanner : CodeScanner
    private val viewModel : ScanBarcodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener()
        getPermission()
        startScanner()
    }

    private fun listener() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuFragment::class.java)
            startActivity(intent)
        }
    }
    private fun getPermission() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQ)
        }
    }
    private fun startScanner() {
        codeScanner = CodeScanner(this, binding.viewScanner)
        codeScanner.apply {
            isPreviewActive
            startPreview()
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isTouchFocusEnabled = true
            isFlashEnabled = false
            decodeCallback = DecodeCallback {
                runOnUiThread {
                    try {
                        val result = binding.tvCode.text.toString().trim()
                        Log.d("BARCODESS", result)
                        getProductByBarcode(result)
                    }catch (e : Exception) {
                        Log.d("TAG", e.localizedMessage.orEmpty())
                    }
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread {
                    Toast.makeText(this@ScanActivity, "Camera Not Initialize",
                        Toast.LENGTH_SHORT).show()
                }
            }
            binding.viewScanner.setOnClickListener {
                reScan()
            }
        }
    }
    private fun reScan() {
        codeScanner.startPreview()
        binding.tvCode.text = "Z6F5L323107-10700726"
    }
    private fun getProductByBarcode(barcode : String) {
        viewModel.getProductByBarcode(barcode).observe(this){ response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            handleProduct(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context,
                            "Failed get barcode",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun handleProduct(product : ProductsByIdModel) {
        val intent = Intent(this, ExpandItemPagerTwoActivity::class.java)
            .putExtra(ExpandItemPagerTwoActivity.EXTRA_BARCODE, product )
        startActivity(intent)
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    companion object {
        const val CAMERA_REQ = 101
    }
}