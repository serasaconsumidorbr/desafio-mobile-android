package com.example.feature.hero_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.heroes.model.Hero
import com.example.feature.hero_list.databinding.CarouselItemBinding

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var items: List<Hero> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarouselItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size


    inner class ViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hero) {
            binding.item = item
            binding.imageView.load(item.thumbnail?.path?.replace("http","https")+"."+item.thumbnail?.extension)
            binding.executePendingBindings()
        }
    }

    fun updateItems(newItems: List<Hero>) {
        items = newItems
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}