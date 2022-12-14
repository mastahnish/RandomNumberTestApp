package com.modemobile.jacekrys.randomnumbertestapp.data.remote

import retrofit2.http.GET

interface RandomNumberApi {

    @GET("v1.0/randomnumber")
    suspend fun getNumber(): List<Int>
}