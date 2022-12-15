package com.modemobile.jacekrys.randomnumbertestapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomNumberApi {

    @GET("v1.0/randomnumber")
    suspend fun getNumber(@Query ("min") min : Int, @Query("max") max : Int): List<Int>
}
