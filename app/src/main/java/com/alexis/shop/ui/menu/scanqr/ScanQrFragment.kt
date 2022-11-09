package com.alexis.shop.ui.menu.scanqr

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentScanQrBinding
import com.alexis.shop.domain.model.product.ProductsByIdModel
import com.alexis.shop.ui.detail.ExpanItemPagersActivity
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import com.budiyev.android.codescanner.*
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_whereFrom = "whereFrom"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ScanQrFragment : BaseFragment<FragmentScanQrBinding>() {
    private var whereFrom: Int? = null
    private var param2: String? = null
    private lateinit var codeScanner: CodeScanner
    private val viewModel by viewModels<ScanBarcodeViewModel>()

    override fun getViewBinding() = FragmentScanQrBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            whereFrom = it.getInt(ARG_whereFrom)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        getPermission()
        startScanner()
        reScan()

        binding.btnCancel.setOnClickListener {
            justOut()
        }

        binding.btnBack.setOnClickListener {
            if (whereFrom == MENU_FRAGMENT) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    private fun getPermission() {
         val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
         if (permission != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_CODE)
        }
    }


    private fun startScanner() {
        codeScanner = CodeScanner(requireContext(), binding.viewScanner)
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
                activity?.runOnUiThread {
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
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), "Camera Not Initialize",
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
        binding.tvCode.text = "Scanning"
    }

    private fun getProductByBarcode(barcode : String) {
        viewModel.getProductByBarcode(barcode).observe(viewLifecycleOwner){ response ->
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
        val intent = Intent(requireContext(), ExpanItemPagersActivity::class.java)
            .putExtra(ExpanItemPagersActivity.EXTRA_DATA, product )
        startActivity(intent)
    }
//    private val permReqLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//            if (it) {
//                startScanner()
//            } else {
//                Toast.makeText(
//                    binding.root.context,
//                    "You need to allow camera permission",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }

    private fun hasPermissions(context: Context, permissions: String): Boolean =
        ActivityCompat.checkSelfPermission(
            context,
            permissions
        ) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val MENU_FRAGMENT = 1
        var CAMERA_PERMISSIONS = Manifest.permission.CAMERA
        const val CAMERA_CODE = 100

        @JvmStatic
        fun newInstance(whereFrom: Int, param2: String) =
            ScanQrFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_whereFrom, whereFrom)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}