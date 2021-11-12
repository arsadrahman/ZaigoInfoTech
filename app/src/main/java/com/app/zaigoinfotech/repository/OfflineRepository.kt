package com.app.zaigoinfotech.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.app.zaigoinfotech.model.DataLogin
import com.app.zaigoinfotech.utility.Constants
import com.google.gson.GsonBuilder
import javax.inject.Inject

class OfflineRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun updateUserData(userDetails: DataLogin) {
        var userString: String? = GsonBuilder().create().toJson(userDetails)
        sharedPreferences.edit().putString(Constants.userDetails, userString).apply()
    }

    fun logout() {
        sharedPreferences.edit().putString(Constants.userDetails, null).apply()
    }

    fun isUserLoggedIn(isUserLoggedIn: MutableLiveData<Boolean>) {
        var userDetails = sharedPreferences.getString(Constants.userDetails, null)
        isUserLoggedIn.postValue(userDetails != null)
    }

    fun getUser(userDetails: MutableLiveData<DataLogin>) {
        var userString = sharedPreferences.getString(Constants.userDetails, null)
        userDetails.postValue(GsonBuilder().create().fromJson(userString, DataLogin::class.java))
    }

}