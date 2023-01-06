package com.alexis.shop.ui.account.point

import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.alexis.shop.databinding.FragmentPointsBinding
import com.alexis.shop.domain.model.points.PointItemModel
import com.alexis.shop.ui.menu.adapter.PointsAdapter
import com.alexis.shop.utils.*
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_points.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class PointsFragment : BaseFragment<FragmentPointsBinding>() {
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel : PointViewModel by viewModels()
    private var pointList = ArrayList<PointItemModel>()
    private lateinit var adapterPoint : PointsAdapter
    override fun getViewBinding() = FragmentPointsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        super.main()

        blurView()
        binding.btnCancel.setOnClickListener {
            justOut()
        }
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        adapterPoint = PointsAdapter(binding.root.context, object : OnClickItem{
            override fun onClick(item: Any) {
                item as PointItemModel
                Toast.makeText(requireContext(), "Clicked ${item.point}", Toast.LENGTH_SHORT).show()
            }
        })
        with(binding.recyclePoints){
            layoutManager = LinearLayoutManager(context)
            adapter = adapterPoint
        }
        getPointHistory()

    }

    private fun getPointHistory() {
        viewModel.getPointHistory().observe(viewLifecycleOwner) {response ->
            if (response != null){
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val pointValue = response.data?.points as ArrayList<PointItemModel>
                        pointList = pointValue
                        adapterPoint.setData(pointValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "${response.message}",
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
        blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PointsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}