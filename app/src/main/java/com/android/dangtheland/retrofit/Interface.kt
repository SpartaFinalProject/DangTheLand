package com.android.dangtheland.retrofit

import com.android.dangtheland.retrofit.abandonedDog.AbandonedDog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {

    @GET("abandonmentPublic")
    fun abandonedDogSearch(
        @Query("serviceKey") serviceKey : String = Constants.AUTH_HEADER,
        @Query("upkind") upkind : Int,
        @Query("_type") type : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("kind") kind : String
    ): Call<AbandonedDog?>
}