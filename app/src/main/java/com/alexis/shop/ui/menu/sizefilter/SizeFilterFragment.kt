package com.alexis.shop.ui.menu.sizefilter

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SizeFilterFragment : Fragment() {
    private val viewModel: SizeFilterViewModel by viewModels()
    private val sizeAdapter = SizeFilterAdapter()
    private lateinit var cancel_btn: ImageView
    private lateinit var textTitle: TextView
    private lateinit var textSizeGuide: TextView
    private lateinit var textSetAsDefault: TextView
    private lateinit var recyclerViewSize: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_size_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.init(view)

        getSizeFilterData()

        Animations.runAnimation(
            requireActivity(),
            Animations.ANIMATION_IN,
            null,
            recyclerViewSize,
            this.textSetAsDefault,
            this.textSizeGuide,
            this.textTitle
        )

        cancel_btn.setOnClickListener {
            Animations.runAnimation(
                requireActivity(),
                Animations.ANIMATION_OUT,
                null,
                recyclerViewSize,
                this.textSetAsDefault,
                this.textSizeGuide,
                this.textTitle
            )

            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().supportFragmentManager.popBackStack()
            }, 1200L)

        }
    }

    private fun init(view: View) {
        this.cancel_btn = view.findViewById(R.id.btn_cancel)
        this.textTitle = view.findViewById(R.id.textTitle)
        this.textSizeGuide = view.findViewById(R.id.textSizeGuide)
        this.textSetAsDefault = view.findViewById(R.id.textSetAsDefault)
        this.recyclerViewSize = view.findViewById(R.id.recycler_view_size_parent)
    }

    private fun getSizeFilterData() {
        viewModel.getSizeFilter().observe(viewLifecycleOwner) { response ->
            Log.d("test123", response.toString())
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        separateData(response.data!!)
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "Get size filter failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Separate data based on category
    private fun separateData(data: List<SizeFilterModel>) {
        val dataBottoms = data.filter { it.selection.lowercase() == "bottoms"}
        val dataTops = data.filter { it.selection.lowercase() == "tops"}
        val dataShoes = data.filter { it.selection.lowercase() == "shoes"}
        val mainData = ArrayList<List<SizeFilterModel>>()
        mainData.add(dataTops)
        mainData.add(dataBottoms)
        mainData.add(dataShoes)

        initRecyclerView(mainData)
    }

    private fun initRecyclerView(mainData: ArrayList<List<SizeFilterModel>>) {
        sizeAdapter.setData(mainData)
        recyclerViewSize.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        recyclerViewSize.setHasFixedSize(true)
        recyclerViewSize.adapter = sizeAdapter
    }
}