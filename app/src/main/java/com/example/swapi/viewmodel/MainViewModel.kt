package com.example.swapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.data.*
import com.example.swapi.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList

class MainViewModel : ViewModel() {

    private val _nosignal = MutableLiveData<String>()
    private val _loading = MutableLiveData<Boolean>()
    private val _status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    lateinit var people: MutableLiveData<PeopleResponse>

    init {
        people = MutableLiveData()
    }

    fun getPeopleObserver(): MutableLiveData<PeopleResponse> {
        return people
    }

    fun getPlanet(): LiveData<ArrayList<Planets>> {
        val planet = MutableLiveData<ArrayList<Planets>>()
        _loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getPlanet()
                if (data.isSuccessful) {
                    planet.value = data.body()?.results
                } else {
                    _status.value = data.code()
                }
                _loading.value = false
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> message.postValue(t.localizedMessage)
                    is HttpException -> message.value = t.message().toString()
                    else -> message.postValue(t.localizedMessage)
                }
                _loading.value = false
            }
        }
        return planet
    }

    fun getPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstace = ApiClient.getClient()
            val response = retroInstace.getPeople()
            try {
                _loading.postValue(false)
                people.postValue(response)
            } catch (throwable: Throwable) {
                _nosignal.postValue(throwable.localizedMessage)
            }

        }
    }

    fun getSpecies(): LiveData<ArrayList<Species>> {
        val species = MutableLiveData<ArrayList<Species>>()
        _loading.postValue(true)
        ApiClient.getClient().getSpecies().enqueue(object : Callback<SpeciesResponse> {
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
    fun getMessage(): LiveData<String> = message

}