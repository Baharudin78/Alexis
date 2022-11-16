package com.alexis.shop.ui.menu.sizefilter

import android.content.Intent
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
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentSizeFilterBinding
import com.alexis.shop.domain.model.sizefilter.SizeFilterModel
import com.alexis.shop.ui.detail.SubCategoryPageActivity
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.log
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_size_filter.*

@AndroidEntryPoint
class SizeFilterFragment : Fragment(), OnClickItem{
    private val viewModel: SizeFilterViewModel by viewModels()
    private val sizeAdapter = SizeFilterAdapter(this)
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
        blurView()
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
            }, 1000L)

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

    private fun postSizeFilter(sizeId : List<Int>) {
        viewModel.postSizeFilter(sizeId).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let { it ->
                            it.map { product ->
                                val intent = Intent(requireContext(), SubCategoryPageActivity::class.java)
                                    .putExtra("DATA", product)
                                startActivity(intent)
                            }
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
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
    private fun initRecyclerView(mainData: ArrayList<List<SizeFilterModel>>) {
        sizeAdapter.setData(mainData)
        recyclerViewSize.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        recyclerViewSize.setHasFixedSize(true)
        recyclerViewSize.adapter = sizeAdapter
    }

    override fun onClick(item: Any) {
        item as SizeFilterModel
        val listSizeId : List<Int> = listOf(item.id.toInt())
        btnSubmit.setOnClickListener {
            postSizeFilter(listSizeId)
        }
        log("$listSizeId")
        Log.d("LSIFAFDA", "${listSizeId.toList()}")

    }
}