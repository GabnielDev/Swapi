package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapi.adapter.PlanetAdapter
import com.example.swapi.data.Planets
import com.example.swapi.databinding.ActivityPlanetBinding
import com.example.swapi.viewmodel.MainViewModel

class PlanetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanetBinding

    lateinit var mainViewModel: MainViewModel
    lateinit var planetAdapter: PlanetAdapter

    private var loading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet)

        binding = ActivityPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
        getData()


    }

    fun setAdapter() {
        planetAdapter = PlanetAdapter()
        planetAdapter.notifyDataSetChanged()

        binding.rvPlanet.apply {
            layoutManager = LinearLayoutManager(this@PlanetActivity, LinearLayoutManager.VERTICAL, false)
            adapter = planetAdapter
        }
    }

    fun setViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun getData() {
        getPlanets()
        getLoading()

    }

    fun getPlanets() {
        mainViewModel.getPlanet().observe(this, {
            planetAdapter.setData(it)
        })
        mainViewModel.getPlanet()
    }

    fun getLoading() {
        mainViewModel.getLoading().observe(this, {
            loading = it
            if (loading) binding.progressCircular.visibility = View.VISIBLE
            else binding.progressCircular.visibility = View.GONE
        })

        mainViewModel.getStatus().observe(this, {
            if (it >= 400) binding.lineNodata.visibility = View.GONE
        })
        mainViewModel.getMessage().observe(this, {
            if (!it.isNullOrEmpty()) binding.lineNodata.visibility = View.VISIBLE
            binding.rvPlanet.visibility = View.GONE

            if (it.isNullOrEmpty()) binding.lineNodata.visibility = View.GONE
        })
    }

}