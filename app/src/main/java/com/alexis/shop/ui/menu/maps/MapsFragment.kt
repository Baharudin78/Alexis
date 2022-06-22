package com.alexis.shop.ui.menu.maps

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alexis.shop.R
import com.alexis.shop.domain.model.store_location.StoreLocationByNameModel
import com.alexis.shop.utils.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private var storeLocationByNameModel: StoreLocationByNameModel? = null

    lateinit var title: TextView
    lateinit var mapFragment: SupportMapFragment
    lateinit var btn_close: ImageView
    lateinit var btn_back: ImageView
    lateinit var btn_goto_maps: ImageView

    companion object {
        private const val ARG_PARAM1 = "param1"

        fun newInstance(param1: StoreLocationByNameModel): MapsFragment {
            val fragment = MapsFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, param1)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        if (arguments != null) {
            storeLocationByNameModel = arguments?.getSerializable(ARG_PARAM1) as? StoreLocationByNameModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_maps, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        title = root.findViewById(R.id.titele)
        btn_close = root.findViewById(R.id.btn_cancel)
        btn_back = root.findViewById(R.id.btn_back)
        btn_goto_maps = root.findViewById(R.id.btn_gmaps)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_close.setOnClickListener {
            justOut()
        }

        btn_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        btn_goto_maps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/@-7.7877267,110.3459157,15z?hl=id"))
            startActivity(intent)
        }

        title.text = storeLocationByNameModel?.city
        mapFragment.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val bandung = LatLng(-6.9034443, 107.5731162)

        val marker = MarkerOptions().position(bandung)
                .icon(BitmapDescriptorFactory.fromBitmap(makeBitmap()))

        googleMap.addMarker(marker).showInfoWindow()
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_monochrome_style)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bandung, 15f))
    }

    private fun makeBitmap(): Bitmap {
        val linear = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        val img = ImageView(requireContext()).apply {
            setImageResource(R.drawable.ic_alexis_round)
        }
        val ttl = TextView(requireContext()).apply {
            text = storeLocationByNameModel?.city
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

        val param = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, 120)
        linear.addView(img, param)
        linear.addView(ttl, param)
        linear.layoutParams = param

        val view: View = linear
        view.setMeasure()

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }

}