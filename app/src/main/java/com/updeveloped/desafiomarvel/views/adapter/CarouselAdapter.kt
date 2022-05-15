package com.updeveloped.desafiomarvel.views.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.views.viewHolder.CarouselItemViewHolder

class CarouselAdapter(private var results: List<CharacterDetail>) : RecyclerView.Adapter<CarouselItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        return CarouselItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val character = results[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}
