package com.example.codechallengeyape.presentation.map

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.codechallengeyape.R
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationFragment: SupportMapFragment() {

    private val parentViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentViewModel.selectedRecipe.observe(viewLifecycleOwner) { selectedRecipe ->
            getMapAsync { map ->
                val location = selectedRecipe.location
                val house = LatLng(location.latitude, location.longitude)
                map.addMarker(
                    MarkerOptions()
                        .position(house)
                )
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(house, 16.0f))
            }
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = getString(R.string.location_title)
        }
    }
}