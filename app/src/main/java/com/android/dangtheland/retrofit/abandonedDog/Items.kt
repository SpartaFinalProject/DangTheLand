package com.android.dangtheland.retrofit.abandonedDog


import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("item")
    val item: List<Item>
)