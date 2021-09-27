package com.example.swapi.data

data class PeopleResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: ArrayList<People>
)