package com.app.zaigoinfotech.repository

import com.app.zaigoinfotech.model.LoginResult
import com.app.zaigoinfotech.model.SweepstakeListResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkInterface {

    //Login
    @FormUrlEncoded
    @POST("prize_junkies/api/auth/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResult

    //Getting Sweepstake List
    @GET("prize_junkys/api/sweepstake")
    suspend fun sweepStacksList(): SweepstakeListResult


}