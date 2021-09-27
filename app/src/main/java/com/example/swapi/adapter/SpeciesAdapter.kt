package com.example.swapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.data.Species
import com.example.swapi.databinding.ItemSpeciesBinding

class SpeciesAdapter: RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private val listData = arrayListOf<Species>()

    fun setData(list: MutableList<Species>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSpeciesBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Species) {
            with(binding) {
                txtName.text = "Name : " + data.name
                txtClassification.text = "Classification : " + data.classification
                txtDesignation.text = "Designation : " + data.designation
                txtAverageHeigh.text = "Average Height : " + data.averageHeight
                txtLanguage.text = "Language : " + data.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSpeciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}