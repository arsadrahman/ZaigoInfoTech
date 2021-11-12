package com.app.zaigoinfotech.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.zaigoinfotech.model.DataLogin
import com.app.zaigoinfotech.model.LoginResult
import com.app.zaigoinfotech.model.SweepstakeListResult
import com.app.zaigoinfotech.repository.OfflineRepository
import com.app.zaigoinfotech.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val offlineRepository: OfflineRepository
) : ViewModel() {

    var showMessage: MutableLiveData<String> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isPasswordError: MutableLiveData<Boolean> = MutableLiveData()
    var isEmailAddressError: MutableLiveData<Boolean> = MutableLiveData()
    var isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    var userDetails:MutableLiveData<DataLogin> = MutableLiveData()

    fun getUser(){
        offlineRepository.getUser(userDetails)
    }

    fun checkUserLoggedIn() {
        offlineRepository.isUserLoggedIn(isUserLoggedIn)
    }

    fun updateUserDetails(userDetails: DataLogin) {
        offlineRepository.updateUserData(userDetails)
    }
    fun logout(){
        offlineRepository.logout()
    }

    fun loginUser(email: String, password: String) = liveData(Dispatchers.IO) {

        if (TextUtils.isEmpty(email)) {
            isEmailAddressError.postValue(true)
        } else if (TextUtils.isEmpty(password)) {
            isPasswordError.postValue(true)
        } else {

            isLoading.postValue(true)
            try {
                val loginResult: LoginResult = repository.loginUser(email, password)
                //If login successfully will emit the user details else will show the error
                loginResult.data?.let { userData ->
                    emit(userData)
                    Log.e("Yes", userData.toString())

                    //Offline User Data Updated
                    updateUserDetails(userData)
                    isLoading.postValue(false)
                    loginResult.message?.let { message -> showMessage.postValue(message) }
                    return@liveData
                }

                isLoading.postValue(false)
                loginResult.message?.let { message ->
                    showMessage.postValue("Error : $message")
                }
            } catch (ex: Exception) {
                showMessage.postValue("Error ${ex.message}")
                isLoading.postValue(false)
            }
        }
    }

    fun getSweepstackesList() = liveData(Dispatchers.IO) {
        isLoading.postValue(true)
        try {
            val listResult: SweepstakeListResult = repository.getSweepstakesList()
            listResult.stakes?.let { stacks ->
                stacks.data?.let { sweepList ->
                    emit(sweepList)
                    isLoading.postValue(false)
                }
            }
            isLoading.postValue(false)


        } catch (ex: Exception) {
            showMessage.postValue("Error ${ex.message}")
            isLoading.postValue(false)
        }
    }





}