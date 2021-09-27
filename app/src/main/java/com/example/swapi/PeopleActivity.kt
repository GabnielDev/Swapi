package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapi.adapter.PeopleAdapter
import com.example.swapi.data.People
import com.example.swapi.databinding.ActivityPeopleBinding
import com.example.swapi.viewmodel.MainViewModel

class PeopleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeopleBinding

    lateinit var mainViewModel: MainViewModel
    lateinit var peopleAdapter: PeopleAdapter

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        binding = ActivityPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUpViewModel()
        getData()
        getNoSignal()

    }

    fun setAdapter() {
        peopleAdapter = PeopleAdapter()
        peopleAdapter.notifyDataSetChanged()

        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(this@PeopleActivity, LinearLayoutManager.VERTICAL, false)
            adapter = peopleAdapter
        }
    }

    fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun getData() {
        getPeoples()
        getLoading()
    }

    fun getPeoples() {
        mainViewModel.getPeople().observe(this, {
            peopleAdapter.setData(it)
        })
        mainViewModel.getPeople()
    }

    fun getNoSignal() {
        mainViewModel.getNoSignal().observe(this, {
            if (!it.isNullOrEmpty()) binding.lineNodata.visibility = View.VISIBLE
            binding.rvPeople.visibility = View.GONE

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