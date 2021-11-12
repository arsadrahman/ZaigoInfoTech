package com.app.zaigoinfotech.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MapRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val coder: Geocoder
) {

    fun getLocationFromAddress(strAddress: String?, latlng: MutableLiveData<LatLng>,isLoading:MutableLiveData<Boolean>) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val address: List<Address>?
            var p1: LatLng? = null
            try {

                address = coder.getFromLocationName(strAddress, 5)
                isLoading.postValue(false)
                if (address == null) {
                    return@launch
                }

                val location: Address = address[0]
                location.latitude
                location.longitude
                p1 = LatLng(location.latitude, location.longitude)

            } catch (e: Exception) {
                isLoading.postValue(false)
                e.printStackTrace()
            }
            latlng.postValue(p1)
        }
    }
}