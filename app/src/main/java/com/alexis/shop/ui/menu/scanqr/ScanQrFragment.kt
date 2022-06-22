package com.alexis.shop.ui.menu.scanqr

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.alexis.shop.BaseFragment
import com.alexis.shop.databinding.FragmentScanQrBinding
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
    private var codeScanner: CodeScanner? = null

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
        if (!hasPermissions(activity as Context, CAMERA_PERMISSIONS)) {
            permReqLauncher.launch(CAMERA_PERMISSIONS)
        }

        binding.btnCancel.setOnClickListener {
            justOut()
        }

        binding.btnBack.setOnClickListener {
            if (whereFrom == MENU_FRAGMENT) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        startScanner()
    }

    override fun onResume() {
        super.onResume()
        codeScanner?.startPreview()
    }

    override fun onPause() {
        codeScanner?.releaseResources()
        super.onPause()
    }

    private fun startScanner() {
        if (codeScanner == null) {
            val viewScanner = binding.viewScanner
            codeScanner = CodeScanner(binding.root.context, viewScanner)

            codeScanner?.apply {
                camera = CodeScanner.CAMERA_BACK
                formats = CodeScanner.ALL_FORMATS

                autoFocusMode = AutoFocusMode.SAFE
                scanMode = ScanMode.CONTINUOUS
                isAutoFocusEnabled = true
                isFlashEnabled = false

                decodeCallback = DecodeCallback {
                    requireActivity().runOnUiThread {
                        Toast.makeText(binding.root.context, it.text, Toast.LENGTH_SHORT).show()
                    }
                }

                errorCallback = ErrorCallback {
                    requireActivity().runOnUiThread {
                        Log.e("Main", "codeScanner: ${it.message}")
                    }
                }

                viewScanner.setOnClickListener {
                    codeScanner?.startPreview()
                }
            }
        }
    }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                startScanner()
            } else {
                Toast.makeText(
                    binding.root.context,
                    "You need to allow camera permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun hasPermissions(context: Context, permissions: String): Boolean =
        ActivityCompat.checkSelfPermission(
            context,
            permissions
        ) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val MENU_FRAGMENT = 1
        var CAMERA_PERMISSIONS = Manifest.permission.CAMERA

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