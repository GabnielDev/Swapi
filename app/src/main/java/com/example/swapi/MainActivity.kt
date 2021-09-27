package com.example.swapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swapi.databinding.ActivityMainBinding
import com.example.swapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPeople.setOnClickListener {
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlanet.setOnClickListener {
            val intent = Intent(this, PlanetActivity::class.java)
            startActivity(intent)
        }

        binding.btnSpecies.setOnClickListener {
            val intent = Intent(this, SpeciesActivity::class.java)
            startActivity(intent)
        }

    }

}