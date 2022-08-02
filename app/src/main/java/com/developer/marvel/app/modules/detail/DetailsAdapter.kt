package com.developer.marvel.app.modules.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.marvel.databinding.CarouselDetailBinding
import com.developer.marvel.domain.entities.Job


class DetailsAdapter(comics: Job, series: Job, stories: Job, events: Job) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    private val details = listOf(comics, series, stories, events).filter {
        it.items.isNotEmpty()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsAdapter.ViewHolder {
        val binding =
            CarouselDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        val detail = details[position]
        holder.binding.textTitle.text = detail.type.label
        holder.binding.recyclerJobs.adapter = JobsAdapter(detail)
        holder.binding.recyclerJobs.setHasFixedSize(true)
    }

    override fun getItemCount(): Int = details.size


    inner class ViewHolder(val binding: CarouselDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}