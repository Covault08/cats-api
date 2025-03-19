package com.example.cat_api.data.model


import com.google.gson.annotations.SerializedName

data class CatsModelItem(
    @SerializedName("breeds")
    val breeds: List<Breed> = listOf(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
)