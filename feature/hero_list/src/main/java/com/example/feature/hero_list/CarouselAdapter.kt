package com.example.feature.hero_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.heroes.model.Hero
import com.example.feature.hero_list.databinding.CarouselItemBinding

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.ViewHolder> {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarouselItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hero) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}