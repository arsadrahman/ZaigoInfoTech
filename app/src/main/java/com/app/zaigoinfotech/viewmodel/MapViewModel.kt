package com.app.zaigoinfotech.viewmodel

import android.location.Address
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.zaigoinfotech.repository.MapRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(val mapRepository: MapRepository) : ViewModel() {
    var locationLatLng: MutableLiveData<LatLng> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getLatLngFromAddress(address: String) {
        mapRepository.getLocationFromAddress(address, locationLatLng, isLoading)
    }


}