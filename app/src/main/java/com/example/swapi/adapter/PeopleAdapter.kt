package com.example.swapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.data.People
import com.example.swapi.databinding.ItemPeopleBinding

class PeopleAdapter: RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val listData = arrayListOf<People>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<People>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: People) {
            with(binding) {
                txtName.text = "Name : " + data.name
                txtHeight.text = "Heigh : " + data.height
                txtMass.text = "Mass : " + data.mass
                txtHairColor.text = "Hair Color : " + data.hair_color
                txtSkinColor.text = "Skin Color : " + data.skin_color
                txtEyeColor.text = "Eye Color : " + data.eye_color
                txtBirth.text = "Birth Year : " + data.birth_year
                txtGender.text = "Gender : " + data.gender
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}