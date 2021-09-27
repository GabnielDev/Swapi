package com.example.swapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapi.data.*
import com.example.swapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainViewModel : ViewModel() {

    private val _nosignal = MutableLiveData<String>()
    private val _loading = MutableLiveData<Boolean>()
    private val _status = MutableLiveData<Int>()

    fun getPeople(): LiveData<ArrayList<People>> {
        val people = MutableLiveData<ArrayList<People>>()
        ApiClient.getClient().getPeople().enqueue(object : Callback<PeopleResponse> {
            override fun onResponse(
                call: Call<PeopleResponse>,
                response: Response<PeopleResponse>
            ) {
                _loading.postValue(false)
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    people.postValue(data)
                } else {
                    _status.postValue(response.code())
                }
            }

            override fun onFailure(call: Call<PeopleResponse>, t: Throwable) {
                people.postValue(null)
            }
        })
        return people
    }

    fun getPlanet(): LiveData<ArrayList<Planets>> {
        val planet = MutableLiveData<ArrayList<Planets>>()
        _loading.postValue(true)
        ApiClient.getClient().getPlanet().enqueue(object : Callback<PlanetsResponse> {
            override fun onResponse(
                call: Call<PlanetsResponse>,
                response: Response<PlanetsResponse>
            ) {
                _loading.postValue(false)
                if (response.isSuccessful) {
                    val data = response.body()?.planets
                    planet.postValue(data)
                } else {
                    _status.postValue(response.code())
                }
            }

            override fun onFailure(call: Call<PlanetsResponse>, t: Throwable) {
                _loading.postValue(false)
                _nosignal.postValue(t.localizedMessage)
            }
        })
        return planet
    }

    fun getSpecies(): LiveData<ArrayList<Species>> {
        val species = MutableLiveData<ArrayList<Species>>()
        _loading.postValue(true)
        ApiClient.getClient().getSpecies().enqueue(object : Callback<SpeciesResponse>{
            override fun onResponse(
                call: Call<SpeciesResponse>,
                response: Response<SpeciesResponse>
            ) {
                _loading.postValue(false)
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    species.postValue(data)
                } else {
                    _status.postValue(response.code())
                }
            }

            override fun onFailure(call: Call<SpeciesResponse>, t: Throwable) {
                _loading.postValue(false)
                _nosignal.postValue(t.localizedMessage)
            }
        })
        return species
    }

    fun getNoSignal(): LiveData<String> = _nosignal
    fun getLoading(): LiveData<Boolean> = _loading
    fun getStatus(): LiveData<Int> = _status

}