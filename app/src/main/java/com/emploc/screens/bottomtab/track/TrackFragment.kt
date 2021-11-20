package com.emploc.screens.bottomtab.track

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emploc.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tasks.view.*
import kotlinx.android.synthetic.main.fragment_track.view.*


class TrackFragment : DaggerFragment(), OnMapReadyCallback {
    var behavior: BottomSheetBehavior<View>? = null
    private lateinit var rootView: View
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_track, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.trackMap) as SupportMapFragment?

        mapFragment?.getMapAsync(this)

        behavior = BottomSheetBehavior.from(rootView.trackBottomSheet)
        behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        behavior?.peekHeight = 220
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0

    }

}