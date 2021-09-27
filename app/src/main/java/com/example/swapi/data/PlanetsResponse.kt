package com.example.swapi.data


import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val planets: ArrayList<Planets>
)