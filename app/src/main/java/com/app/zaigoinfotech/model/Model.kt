package com.app.zaigoinfotech.model

import com.google.gson.annotations.SerializedName


// Sweepstake List Result Model
data class SweepstakeListResult(
    @SerializedName("stakes") var stakes: Stakes
)

data class Stakes(
    @SerializedName("current_page") var currentPage: Int,
    @SerializedName("data") var data: List<Data>,
    @SerializedName("first_page_url") var firstPageUrl: String,
    @SerializedName("from") var from: Int,
    @SerializedName("last_page") var lastPage: Int,
    @SerializedName("last_page_url") var lastPageUrl: String,
    @SerializedName("links") var links: List<Links>,
    @SerializedName("next_page_url") var nextPageUrl: String,
    @SerializedName("path") var path: String,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("prev_page_url") var prevPageUrl: String,
    @SerializedName("to") var to: Int,
    @SerializedName("total") var total: Int
)

data class Links(
    @SerializedName("url") var url: String,
    @SerializedName("label") var label: String,
    @SerializedName("active") var active: Boolean
)

data class Data(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("start_date") var startDate: String,
    @SerializedName("end_date") var endDate: String,
    @SerializedName("status") var status: String,
    @SerializedName("image") var image: String,
    @SerializedName("subscribe") var subscribe: String
)


//Login Result  Model

data class LoginResult (
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : DataLogin
)
data class DataLogin (
    @SerializedName("access_token") val access_token : String,
    @SerializedName("token_type") val token_type : String,
    @SerializedName("user_details") val user_details : UserDetails
)
data class UserDetails (
    @SerializedName("id") val id : Int,
    @SerializedName("email") val email : String
)