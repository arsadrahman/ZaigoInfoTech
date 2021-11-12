package com.app.zaigoinfotech.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.databinding.ActivityAddressBinding
import com.app.zaigoinfotech.viewmodel.MapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : AppCompatActivity(){
    private lateinit var addressBinding: ActivityAddressBinding
    val mapViewModel: MapViewModel by viewModels()
    lateinit var address: String
    lateinit var locationLatLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressBinding =
            DataBindingUtil.setContentView<ActivityAddressBinding>(
                this,
                R.layout.activity_address
            )


        addressBinding.viewModel = mapViewModel
        addressBinding.lifecycleOwner = this
        addressBinding.activity = this
        mapViewModel.locationLatLng.observe(this, Observer { latLng ->
            locationLatLng = latLng
            var intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("lat",latLng.latitude)
            intent.putExtra("lng",latLng.longitude)
            intent.putExtra("address",address)
            startActivity(intent)
        })

    }

    fun find(view: android.view.View) {
        if (TextUtils.isEmpty(addressBinding.doornoTil.editText?.text)) {
            addressBinding.doornoTil.error = "Please enter the Door No"
        } else if (TextUtils.isEmpty(addressBinding.streetnameTil.editText?.text)) {
            addressBinding.streetnameTil.error = "Please enter the Street Name"
        } else if (TextUtils.isEmpty(addressBinding.cityTil.editText?.text)) {
            addressBinding.cityTil.error = "Please enter the City Name"
        } else if (TextUtils.isEmpty(addressBinding.stateTil.editText?.text)) {
            addressBinding.stateTil.error = "Please enter the State Name"
        } else if (TextUtils.isEmpty(addressBinding.countryTil.editText?.text)) {
            addressBinding.countryTil.error = "Please enter the Country Name"
        } else {
            address =
                "${addressBinding.doornoTil.editText?.text} ,${addressBinding.streetnameTil.editText?.text} , ${addressBinding.cityTil.editText?.text} , ${addressBinding.stateTil.editText?.text} , ${addressBinding.countryTil.editText?.text}"
            mapViewModel.getLatLngFromAddress(address)
        }

    }

}