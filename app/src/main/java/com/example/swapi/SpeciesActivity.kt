package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapi.adapter.SpeciesAdapter
import com.example.swapi.databinding.ActivitySpeciesBinding
import com.example.swapi.viewmodel.MainViewModel

class SpeciesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpeciesBinding

    lateinit var mainViewModel: MainViewModel
    lateinit var speciesAdapter: SpeciesAdapter

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        binding = ActivitySpeciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUpViewModel()
        getData()
        getNoSignal()

    }

    fun setAdapter() {
        speciesAdapter = SpeciesAdapter()
        speciesAdapter.notifyDataSetChanged()

        binding.rvSpecies.apply {
            layoutManager = LinearLayoutManager(this@SpeciesActivity, LinearLayoutManager.VERTICAL, false)
            adapter = speciesAdapter
        }

    }

    fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun getData() {
        getSpecies()
        getLoading()

    }

    fun getSpecies() {
        mainViewModel.getSpecies().observe(this, {
            speciesAdapter.setData(it)
        })
        mainViewModel.getSpecies()
    }

    fun getNoSignal() {
        mainViewModel.getNoSignal().observe(this, {
            if (!it.isNullOrEmpty()) binding.lineNodata.visibility = View.VISIBLE
            binding.rvSpecies.visibility = View.GONE

            if (it.isNullOrEmpty()) binding.lineNodata.visibility = View.GONE
        })

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

    }

}