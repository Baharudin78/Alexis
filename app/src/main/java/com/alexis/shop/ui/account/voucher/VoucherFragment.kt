package com.alexis.shop.ui.account.voucher

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentVoucherBinding
import com.alexis.shop.domain.model.voucher.VoucherItemModel
import com.alexis.shop.ui.menu.adapter.VOUCHER_FRAGMENT
import com.alexis.shop.ui.menu.adapter.VoucherAdapter
import com.alexis.shop.utils.*
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class VoucherFragment : BaseFragment<FragmentVoucherBinding>(), OnClickItem {
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel :VoucherViewModel by viewModels()
    private var allVoucher = ArrayList<VoucherItemModel>()
    private lateinit var voucherAdapter: SimpleVoucherAdapter
    @Inject
    lateinit var sharedPref : SheredPreference
    lateinit var title: TextView
    lateinit var recycle: RecyclerView
    lateinit var cancel_button: ImageView
    lateinit var back_button: ImageView

    override fun getViewBinding(): FragmentVoucherBinding = FragmentVoucherBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        voucherAdapter = SimpleVoucherAdapter(binding.root.context, this)
        with(binding.recycleVoucher) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = voucherAdapter
        }
        getAllVoucher()
    }

    private fun getAllVoucher() {
        viewModel.getAllVoucher(sharedPref.getToken()).observe(viewLifecycleOwner) {response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val getAllVoucherValue = response.data?.data as ArrayList<VoucherItemModel>
                        allVoucher = getAllVoucherValue
                        voucherAdapter.setData(getAllVoucherValue)
                    }
                    is Resource.Error -> {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_voucher, container, false)
        title = root.findViewById(R.id.txt_wl)
        recycle = root.findViewById(R.id.recycle_voucher)
        cancel_button = root.findViewById(R.id.btn_cancel)
        back_button = root.findViewById(R.id.btn_back)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        cancel_button.setOnClickListener {
            justOut()
        }

        val itemList: ArrayList<String> = ArrayList()
        itemList.add("Shipping Refund")
        itemList.add("Birthday")
        itemList.add("Voucher A")
        itemList.add("Voucher Z")

        recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = VoucherAdapter(VOUCHER_FRAGMENT, itemList, object: OnClickItem {
                override fun onClick(item: Any) {
                    item as String
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VoucherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(item: Any) {
        Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
    }


}