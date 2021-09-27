package com.example.swapi.data


import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: ArrayList<Species>
)