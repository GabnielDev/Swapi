package com.example.swapi.network

import com.example.swapi.data.*
import com.example.swapi.utils.Constants.BASE_PEOPLE
import com.example.swapi.utils.Constants.BASE_PLANET
import com.example.swapi.utils.Constants.BASE_SPECIES
import com.example.swapi.utils.Constants.BASE_VEHICLES
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(BASE_PEOPLE)
    suspend fun getPeople(
    ): PeopleResponse

    @GET(BASE_PLANET)
    suspend fun getPlanet(): Response<PlanetsResponse>

    @GET(BASE_SPECIES)
    fun getSpecies(): Call<SpeciesResponse>

}