package com.example.swapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.data.Planets
import com.example.swapi.databinding.ItemPlanetBinding

class PlanetAdapter: RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    private val listData = arrayListOf<Planets>()

    fun setData(list: MutableList<Planets>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Planets) {
            with(binding) {
                txtName.text = "Name : " + data.name
                txtPopulation.text = "Population : " + data.population
                txtDiameter.text = "Diameter : " + data.diameter
                txtGravity.text = "Gravity : " + data.gravity
                txtClimate.text = "Climate : " + data.climate
                txtTerrain.text = "Terrain : " + data.terrain
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}