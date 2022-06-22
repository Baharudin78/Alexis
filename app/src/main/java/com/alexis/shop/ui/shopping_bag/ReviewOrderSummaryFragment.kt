package com.alexis.shop.ui.shopping_bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.alexis.shop.domain.model.product.ImageModel
import com.alexis.shop.databinding.FragmentReviewOrderSummaryForSmallerBinding
import com.alexis.shop.ui.shopping_bag.adapter.ImageOrderButBiggerAdapter
import com.alexis.shop.utils.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReviewOrderSummaryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        handleBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewOrderSummaryForSmallerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val display = requireActivity().windowManager.defaultDisplay

        //Sample Array image on adapter list
        val images = ArrayList<ImageModel>()
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PF-AYAUB-B1-S1.png", false))
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PA-AYAUB-B1-S1.png", false))
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PB-AYAUB-B1-S1.png", false))
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PB-AYAUB-B1-S1.png", false))
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PB-AYAUB-B1-S1.png", false))
        images.add(ImageModel("http://api.myalexis.xyz:3001/uploads/product_image/234234PB-AYAUB-B1-S1.png", false))

        with(binding as FragmentReviewOrderSummaryForSmallerBinding) {
            gilding.resetLottieAnimation()

            btnCancel.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnSubmit.setPushClickListener {
                topUp1stpurchase.visible()
            }

            ok1stpurchase.setOnClickListener {
                topUp1stpurchase.gone()
            }

            totalSavings.setOnClickListener {
                //Popup information total savings
                val param: HashMap<String, String> = HashMap()
                param["product"] = "2.900 gr"
                param["packaging"] = "300 gr"
                (activity as AppCompatActivity).popUpUsingLayout(TOTAL_SAVINGS, param).showAlignBottom(it,-100,0)
            }

            totalWeight.setOnClickListener {
                //Popup information total weight
                val param: HashMap<String, String> = HashMap()
                param["product"] = "2.900 gr"
                param["packaging"] = "300 gr"
                popUpUsingLayout(requireContext(), TOTAL_WEIGHT, param).showAlignBottom(it)
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ImageOrderButBiggerAdapter(context, images) {}
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewOrderSummaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}