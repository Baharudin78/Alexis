package com.alexis.shop.ui.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL
import androidx.viewpager2.widget.ViewPager2.OVER_SCROLL_NEVER
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.data.source.dummy.SizeList
import com.alexis.shop.databinding.BottomsheetAddcartItemBinding
import com.alexis.shop.databinding.FragmentPageBinding
import com.alexis.shop.domain.model.product.*
import com.alexis.shop.domain.model.product.modelbaru.ProductBaruModel
import com.alexis.shop.ui.detail.ExpanItemPagersActivity.Companion.BACK_TO_CART
import com.alexis.shop.ui.detail.ExpanItemPagersActivity.Companion.BACK_TO_WISHLIST
import com.alexis.shop.ui.detail.adapter.ImageOrderAdapter
import com.alexis.shop.ui.detail.adapter.ImageViewPagerItem
import com.alexis.shop.ui.detail.adapter.SizeChooserAdapter
import com.alexis.shop.ui.detail.bottomsheets.TestingUntukMyAccount
import com.alexis.shop.ui.detail.bottomsheets.bottomsheetSizeInfo
import com.alexis.shop.utils.*
import com.alexis.shop.utils.circle_layout_manager.CircularHorizontalMode
import com.alexis.shop.utils.common.withDelay
import com.alexis.shop.utils.prefs.SheredPreference
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PageFragment : Fragment(R.layout.fragment_page) {
    private val viewModel: DetailViewModel by viewModels()

    private val binding: FragmentPageBinding by viewBinding()
    private var product: ProductBaruModel? = null
    lateinit var sharedPref : SheredPreference
    private var productDetailData: ProductsByIdModel? = null
    private lateinit var ivArrayDotsPager: Array<ImageView?>

    private val imagePagerAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var adapterSize: SizeChooserAdapter
    private lateinit var adapterPic: ImageOrderAdapter

    private var arraySize: ArrayList<SizeModel> = ArrayList()
    private var sizeSelected = ""

    private val closeBottomSheetOnBackPressed = object : OnBackPressedCallback(false) {
        var buttonInfo: View? = null
        override fun handleOnBackPressed() {
            buttonInfo?.let { closeMoreInfoBottomSheet(it) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(requireContext())
        arguments?.let {
            product = it.getParcelable(PRODUCT)
            Log.d("FDNFSJDNFSODFN", "$product")
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, closeBottomSheetOnBackPressed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductById()
        setupMoreInfo()
        (requireActivity() as ExpanItemPagersActivity).onAnimateHandler = {
            startAnimation()
        }

        binding.btnAdd2love.setPushClickListener {
            postWishlist()
        }

        binding.imgPointItems.setOnClickListener { v ->
            val cs = "<a>exclusive offer, purchase this item</a><br><a>by redeeming Loyalty Points</a>"
            requireActivity().popUpOnTop(viewLifecycleOwner, cs).show(v)
        }

        binding.btnMoreInfo.setPushClickListener {
            showMoreInfoBottomSheet()
        }

        binding.includeBottomSheet.bottomsheetContainer.apply {
            setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
                override fun onSwipeDown() {
                    requireActivity().onBackPressed()
                }
            })
        }
    }

    private fun getProductById() {
        viewModel.getProductById(product?.product_id.orZero()).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            productDetailData = it
                            setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            getString(R.string.auth_error, "get product by id"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupView(data: ProductsByIdModel) {
        binding.itemName.text = data.productName
        binding.itemPrice.text = getString(R.string.price, data.price.toString())
        binding.point.text = data.exclusiveOffer?.redemptionPoint.toString()
        data.images.forEach { imageData ->
            imagePagerAdapter.add(
                ImageViewPagerItem(
                    binding.root.context,
                    ImageModel(imageData.image, false)
                )
            )
        }

        setupPagerIndicatorDots()

        binding.includePager.pager.apply {
            adapter = imagePagerAdapter
            orientation = ORIENTATION_VERTICAL
            reduceDragSensitivity()
            // indicator.setViewPager(pagerBinding.pager)
        }
        var selectedPosition = 0
        binding.includePager.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Unselect Previous
                (binding.includePager.pagerDots.getChildAt(selectedPosition) as ImageView).setImageResource(R.drawable.indicator_unchoosed)
                (binding.includePager.pagerDots.getChildAt(position) as ImageView).setImageResource(R.drawable.indicator_choosed)
                selectedPosition = position
            }

            override fun onPageSelected(position: Int) = Unit
            override fun onPageScrollStateChanged(state: Int) = Unit
        })
    }

    private fun postWishlist() {
        activity?.let {
            if(product != null) {
                viewModel.postWishlist(product!!.item_code.orEmpty()).observe(viewLifecycleOwner) { response ->
                    if (response != null) {
                        when (response) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                it.setResult(Activity.RESULT_OK, Intent().apply {
                                    putExtra("id", BACK_TO_WISHLIST)
                                })
                                it.finish()
                                it.overridePendingTransition(0, R.anim.activity_out_wishlist)
                                VibrateUtil(it).single()
                            }
                            is Resource.Error -> {
                                Toast.makeText(
                                    it.applicationContext,
                                    getString(R.string.auth_error, "Post Wishlist"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun postShoppingBag(size: String) {
        activity?.let {
            if(product != null) {
                viewModel.postShoppingBag(product?.product_id.toString(),product?.size_id.toString(),product?.stock_keeping_unit.toString()).observe(viewLifecycleOwner) { response ->
                    if (response != null) {
                        when (response) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                val intent: Intent = requireActivity().intent
                                intent.putExtra("id", BACK_TO_CART)
                                requireActivity().apply {
                                    setResult(Activity.RESULT_OK, intent)
                                    finish()
                                    overridePendingTransition(0, R.anim.activity_out_cart)
                                }
                                VibrateUtil(requireContext()).single()
                            }
                            is Resource.Error -> {
                                Toast.makeText(
                                    it.applicationContext,
                                    getString(R.string.auth_error, "Post Wishlist"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupPagerIndicatorDots() {
        val heightWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt()
        ivArrayDotsPager = arrayOfNulls(productDetailData?.images?.size.orZero())
        ivArrayDotsPager.indices.forEach { position ->
            ivArrayDotsPager[position] = ImageView(requireContext())
            val params = LinearLayout.LayoutParams(heightWidth, heightWidth)
            params.setMargins(25, 15, 25, 15)
            ivArrayDotsPager[position]?.layoutParams = params
            ivArrayDotsPager[position]?.setImageResource(R.drawable.indicator_unchoosed)
            ivArrayDotsPager[position]?.setOnClickListener { view -> view.alpha = 1f }
            binding.includePager.pagerDots.addView(ivArrayDotsPager[position])
            binding.includePager.pagerDots.bringToFront()
        }
    }

    //PopUp For AddtoCart's button
    private fun showAddToCartBottomSheet() {
        requireActivity().whiteNavBar()
        val bottomSheet = BottomSheetDialog(
            requireActivity(),
            R.style.AppBottomSheetDialogTheme
        )
        bottomSheet.window?.setDimAmount(0f)
        val binding = BottomsheetAddcartItemBinding.inflate(layoutInflater)
        arraySize = SizeList.getListSize()

   //     val sizeList = productDetailData?.size as ArrayList<ProductsGetByIdSizeModel>
//        adapterSize = SizeChooserAdapter(requireContext(), sizeList) {
//            sizeSelected = it.name
//            log("selected $sizeSelected")
//            binding.greyButtonAddkranjang.gone()
//            binding.tvSelectSize.gone()
//        }

        val linearLayoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)

        with(binding.recySizeChooser) {
            adapter = adapterSize
            layoutManager = linearLayoutManager
            PagerSnapHelper().attachToRecyclerView(this)
            setViewMode(CircularHorizontalMode())
            scrollToPosition(2)
            overScrollMode = OVER_SCROLL_NEVER
        }

        binding.recySizeChooser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                synchronized(this) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val position = linearLayoutManager.findFirstVisibleItemPosition()
//                        sizeList.forEachIndexed { index, item ->
//                            item.isSelected = index == position
//                        }
                        //sizeSelected = sizeList[position].name
//                        adapterSize.updateItems(sizeList)
//                        (0 until arraySize.size).forEach {
//                            if (position >= it) changeThisSize(arraySize[position])
//                        }
                        binding.greyButtonAddkranjang.gone()
                        binding.tvSelectSize.gone()
                    }
                }
            }
        })

        binding.buttonsSize.setPushClickListener {
            bottomSheet.dismiss()
            bottomsheetSizeInfo(requireActivity())
        }

        binding.addtokranjang.setPushClickListener {
            log("there it is $sizeSelected")
            if (sizeSelected == "") {
                binding.greyButtonAddkranjang.visible()
                binding.tvSelectSize.visible()
            } else {
                postShoppingBag(sizeSelected)
            }
        }

        bottomSheet.apply {
            setContentView(binding.root)
            setCanceledOnTouchOutside(false)
            show()
        }
    }

    //PopUp For Detail Information's button
    private fun showMoreInfoBottomSheet() {
        binding.apply {
            closeBottomSheetOnBackPressed.buttonInfo = invisibleDot
            closeBottomSheetOnBackPressed.isEnabled = true

            parent.transitionToEnd()

            withDelay(300) {
                val transformContent = MaterialContainerTransform().apply {
                    startView = invisibleDot
                    endView = content
                    scrimColor = Color.TRANSPARENT
                    duration = 700L

                    addTarget(content)
                    setPathMotion(MaterialArcMotion())
                }

                TransitionManager.beginDelayedTransition(root, transformContent)

                content.motionVisibility(View.VISIBLE)
                btnMoreInfo.motionVisibility(View.INVISIBLE)
            }

            productDetailData?.let {
                with(includeBottomSheet) {
                    price.text = it.price.toString()
                    itemTitle.text = it.productName
                    itemCode.text = it.productId.toString()

                    var material = "Material: "
//                    it.material.forEachIndexed { index, item ->
//                        material += item.name
//                        if(index < it.material.size - 1) {
//                            material += ", "
//                        }
//                    }
                    itemMaterial.text = material
                    itemWeight.text = "Weight: " + getString(R.string.weight, it.weight.toString())

                    adapterPic = ImageOrderAdapter(it.images as ArrayList<ProductsGetByIdImagesModel>) {
                        changeThisPic(it)
                        requireActivity().onBackPressed()
                    }

                    imageSpan.apply {
                        layoutManager = LinearLayoutManager(binding.root.context, HORIZONTAL, false)
                        adapter = adapterPic
                    }

                    setupSizeInfoBottomSheet()
                }
            }
        }
    }

    private fun setupSizeInfoBottomSheet() {
        val allDataSize = arrayOf("xs","s","m","l","xl")
        binding.includeBottomSheet.linearLayoutSizeContainer.weightSum = productDetailData?.size?.size?.toFloat()!!
        for(i in allDataSize) {
            //data that is not on the list
            var missingData = ""
            var count = 0
            for(j in productDetailData?.size!!) {
                if(i == j.name.lowercase()) {
                    break
                } else if (i != j.name.lowercase() && count == productDetailData?.size?.size!! - 1) {
                    missingData = i
                }
                count++
            }

            when(missingData) {
                "xs" -> binding.includeBottomSheet.sizeXs.gone()
                "s" -> binding.includeBottomSheet.sizeS.gone()
                "m" -> binding.includeBottomSheet.sizeM.gone()
                "l" -> binding.includeBottomSheet.sizeL.gone()
                "xl" -> binding.includeBottomSheet.sizeXl.gone()
            }
        }
    }

    private fun closeMoreInfoBottomSheet(view: View) {
        binding.apply {

            closeBottomSheetOnBackPressed.buttonInfo = null
            closeBottomSheetOnBackPressed.isEnabled = false

            val transformContent = MaterialContainerTransform().apply {
                startView = content
                endView = view
                scrimColor = Color.TRANSPARENT
                duration = 700L

                addTarget(view)
            }

            TransitionManager.beginDelayedTransition(root, transformContent)

            btnMoreInfo.motionVisibility(View.VISIBLE)
            content.motionVisibility(View.INVISIBLE)

            withDelay {
                parent.transitionToStart()
            }
        }
    }

    //Setup for popup detail information
    private fun setupMoreInfo() {
        binding.includeBottomSheet.apply {
            val selectedSize = arrayOf("")
            sizeXs.setOnClickListener {
                selectedSize[0] = "XS"
                binding.greyButtonAddkranjang.gone()
                binding.tvSelectSize.gone()
                boldTextSizeChoosen(sizeXs)
                val unChoose = arrayOf(sizeS, sizeM, sizeL, sizeXl)
                normalTextUnChoosen(unChoose)
            }
            sizeS.setOnClickListener {
                selectedSize[0] = "S"
                binding.greyButtonAddkranjang.gone()
                binding.tvSelectSize.gone()
                boldTextSizeChoosen(sizeS)
                val unchoose = arrayOf(sizeXs, sizeM, sizeL, sizeXl)
                normalTextUnChoosen(unchoose)
            }
            sizeM.setOnClickListener {
                selectedSize[0] = "M"
                binding.greyButtonAddkranjang.gone()
                binding.tvSelectSize.gone()
                boldTextSizeChoosen(sizeM)
                val unchoose = arrayOf(sizeXs, sizeS, sizeL, sizeXl)
                normalTextUnChoosen(unchoose)
            }
            sizeL.setOnClickListener {
                selectedSize[0] = "L"
                binding.greyButtonAddkranjang.gone()
                binding.tvSelectSize.gone()
                boldTextSizeChoosen(sizeL)
                val unchoose = arrayOf(sizeS, sizeM, sizeXs, sizeXl)
                normalTextUnChoosen(unchoose)
            }
            sizeXl.setOnClickListener {
                selectedSize[0] = "XL"
                binding.greyButtonAddkranjang.gone()
                binding.tvSelectSize.gone()
                boldTextSizeChoosen(sizeXl)
                val unchoose = arrayOf(sizeS, sizeM, sizeL, sizeXs)
                normalTextUnChoosen(unchoose)
            }


            binding.btnAddToCart.setPushClickListener {
                if (binding.content.isVisible) {
                    if (selectedSize[0] == "") {
                        binding.greyButtonAddkranjang.visible()
                        binding.tvSelectSize.visible()
                    } else {
                        postShoppingBag(selectedSize[0])
                    }
                } else {
                    showAddToCartBottomSheet()
                }
            }

            buttonsDelivery.setPushClickListener {
                TestingUntukMyAccount(requireActivity(), 0)
            }
            buttonsReturn.setPushClickListener {
                TestingUntukMyAccount(requireActivity(), 1)
            }
            buttonsSize.setPushClickListener {
                bottomsheetSizeInfo(requireActivity())
            }
        }
    }

    private fun boldTextSizeChoosen(tv: TextView) {
        tv.setTextAppearance(requireActivity(), R.style.boldText)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(
                requireContext().resources.getColor(R.color.alexis_orange, requireContext().theme)
            )
        } else {
            tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.alexis_orange))
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
    }

    private fun normalTextUnChoosen(tv: Array<TextView>) {
        for (title in tv) {
            title.setTextAppearance(requireActivity(), R.style.regularText)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                title.setTextColor(
                    requireContext().resources.getColor(R.color.grey_700, requireContext().theme)
                )
            } else {
                title.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_800))
            }
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        }
    }

    private fun changeThisSize(bool: SizeModel) {
        val sizes = ArrayList<SizeModel>()
        arraySize.forEach { size ->
            size.isCurrent = (size == bool)
            sizes.add(size)
        }
        arraySize.clear()
        arraySize.addAll(sizes)
        adapterSize.notifyDataSetChanged()
    }


    private fun changeThisPic(bool: ProductsGetByIdImagesModel) {
        val images = ArrayList<ProductsGetByIdImagesModel>()
//        arrayPic.forEach { image ->
//            image.scaled = (image == bool)
//            images.add(image)
//        }
//        arrayPic.clear()
//        arrayPic.addAll(images)
//        adapterPic.notifyDataSetChanged()
    }

    private fun startAnimation() {
        binding.apply {
            listOf(
                btnAdd2love,
                btnAddToCart,
                btnMoreInfo,
                itemPrice,
                itemName,
                rupiah,
                imgPointItems,
                point,
                pageIndicator.root,
                plus
            ).forEach {
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.animate_quit_fragment)
                withDelay(2000) {
                    it.startAnimation(
                        anim
                    )
                }

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) = Unit
                    override fun onAnimationRepeat(animation: Animation?) = Unit
                    override fun onAnimationEnd(animation: Animation?) {
                        it.isVisible = false
                    }
                })
            }
        }
    }

    companion object {
        const val PRODUCT = "product"

        @JvmStatic
        fun newInstance(product: ProductBaruModel) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    Log.d("ANJAYY", product.toString())
                    putParcelable(PRODUCT, product)
                }
            }
    }
}