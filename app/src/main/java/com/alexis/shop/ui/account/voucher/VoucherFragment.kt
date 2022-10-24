package com.alexis.shop.ui.account.voucher

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.BaseFragment
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentVoucherBinding
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class VoucherFragment : BaseFragment<FragmentVoucherBinding>(), OnClickItem {
    private val viewModel :VoucherViewModel by viewModels()
    private var allVoucher = ArrayList<VoucherItemModel>()
    private lateinit var voucherAdapter: SimpleVoucherAdapter
    @Inject
    lateinit var sharedPref : SheredPreference
    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView

    override fun getViewBinding(): FragmentVoucherBinding = FragmentVoucherBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(requireContext())
        handleBackPressed()
    }

    override fun main() {
        voucherAdapter = SimpleVoucherAdapter(binding.root.context, this)
        with(binding.recycleVoucher) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = voucherAdapter
        }
        getAllVoucher()
        setListener()
    }

    private fun getAllVoucher() {
        viewModel.getAllVoucher().observe(viewLifecycleOwner) {response ->
            if (response != null) {
                Log.d("ERRORRS", sharedPref.getToken())
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("ERROR", sharedPref.getToken())
                        val getAllVoucherValue = response.data?.data as ArrayList<VoucherItemModel>
                        allVoucher = getAllVoucherValue
                        voucherAdapter.setData(getAllVoucherValue)
                    }
                    is Resource.Error -> {
                        Log.d("ERRORRS", sharedPref.getToken())
                        Toast.makeText(
                            binding.root.context,
                            "GetVoucherFailed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnCancel.setOnClickListener {
                var index = allVoucher.size
                for (data in allVoucher) {
                    Animations.runAnimation(
                        requireContext(),
                        Animations.ANIMATION_OUT,
                        index,
                        recycleVoucher.layoutManager!!.findViewByPosition(index)!!.rootView
                    )
                    if (index != 1) {
                        index -= 1
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    justOut()
                }, 1000L)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() : VoucherFragment {
            return VoucherFragment()
        }
    }

    override fun onClick(item: Any) {
        Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
    }
}