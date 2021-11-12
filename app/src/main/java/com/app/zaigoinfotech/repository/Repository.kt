package com.app.zaigoinfotech.repository

import javax.inject.Inject

class Repository @Inject constructor(private val networkInterface: NetworkInterface) {

    //Get Sweepstakes List
    suspend fun getSweepstakesList() = networkInterface.sweepStacksList()
    //Login User
    suspend fun loginUser(email: String, password: String) =
        networkInterface.loginUser(email, password)
}