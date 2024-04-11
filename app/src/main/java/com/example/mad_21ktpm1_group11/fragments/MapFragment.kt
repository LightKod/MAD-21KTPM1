package com.example.mad_21ktpm1_group11.fragments

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.ImageURLAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {
    private lateinit var menuBtn: ImageButton
    private lateinit var backBtn: ImageButton
    private lateinit var ticketBtn: ImageButton

    private lateinit var bottom_sheet_layout: CoordinatorLayout

    private lateinit var viewPagerPromotion: ViewPager2
    private lateinit var promotionImageList: ArrayList<String>
    private lateinit var viewPagerPromotionAdapter: ImageURLAdapter

    private lateinit var textViewCinemaAddress: TextView

    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val LOCATIONS = arrayOf(
        LatLng(10.764368270577801, 106.6823860622948),
        LatLng(10.763825353031713, 106.68746293969397),
        LatLng(10.770430176518737, 106.66989703617679)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        init(view)

        return view
    }

    private fun init(view: View){
        menuBtn = view.findViewById(R.id.menuBtn)
        backBtn = view.findViewById(R.id.backBtn)
        ticketBtn = view.findViewById(R.id.ticketBtn)

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        backBtn.setOnClickListener {
            (this.activity as? MainActivity)?.goBack()
        }

        ticketBtn.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(TicketFragment(), "ticket")
        }

        bottom_sheet_layout = view.findViewById(R.id.bottom_sheet_layout)

        viewPagerPromotion = view.findViewById(R.id.viewPagerPromotion)

        promotionImageList = ArrayList()
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/i/m/imgpsh_fullsize_anim_2.png")
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_4_-min_2.jpg")
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_-min.png")
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_-min_11.jpg")
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_1_-min_4.png")
        promotionImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/w/e/web_rolling_banner_980_x_448_px-min.png")

        viewPagerPromotionAdapter = ImageURLAdapter(this, promotionImageList)

        viewPagerPromotion.adapter = viewPagerPromotionAdapter
        viewPagerPromotion.offscreenPageLimit = 2
        viewPagerPromotion.clipToPadding = false
        viewPagerPromotion.clipChildren = false

        val advertisementTransformer = CompositePageTransformer()
        advertisementTransformer.addTransformer(MarginPageTransformer(30))
        viewPagerPromotion.setPageTransformer(advertisementTransformer)

        textViewCinemaAddress = view.findViewById(R.id.textViewCinemaAddress)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            if(hasPermissions(activity as Context, PERMISSIONS)){
                getMap()
            }
            else{
                permReqLauncher.launch(PERMISSIONS)
            }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        LOCATIONS.forEach {
            googleMap.addMarker(MarkerOptions().position(it).title("${it.latitude}, ${it.longitude}"))
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATIONS[0], 20.0f))

        googleMap.setOnMarkerClickListener {
            textViewCinemaAddress.text = it.title
            if(bottom_sheet_layout.visibility == View.GONE){
                bottom_sheet_layout.visibility = View.VISIBLE
                ObjectAnimator.ofFloat(bottom_sheet_layout, "translationY", bottom_sheet_layout.height.toFloat(), 0f).apply {
                    duration = 300
                    start()
                }
            }
            it.hideInfoWindow()
            true
        }

        googleMap.setOnMapClickListener {
            if(bottom_sheet_layout.visibility == View.VISIBLE){
                ObjectAnimator.ofFloat(bottom_sheet_layout, "translationY", 0f, bottom_sheet_layout.height.toFloat()).apply {
                    duration = 300
                    addListener(object: AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            bottom_sheet_layout.visibility = View.GONE
                        }
                    })
                    start()
                }
            }
        }

        if(hasPermissions(activity as Context, PERMISSIONS)) {
            googleMap.isMyLocationEnabled = true
        }
    }

    private fun getMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private val permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val granted = permissions.entries.all{
            it.value
        }
        if(granted){
            Log.i("meo", "permission granted")
            getMap()
        }
        else{
            (this.activity as? MainActivity)?.goBack()
        }
    }
}