package com.developer.marvel.app.modules.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.marvel.R
import com.developer.marvel.databinding.CarouselDetailBinding
import com.developer.marvel.databinding.CarouselDetailItemBinding
import com.developer.marvel.domain.entities.Job
import com.developer.marvel.domain.entities.JobItem
import com.developer.marvel.domain.entities.JobType


class JobsAdapter(private val job: Job) :
    RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobsAdapter.ViewHolder {
        val binding =
            CarouselDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsAdapter.ViewHolder, position: Int) {
        holder.binding.imageCharacter.setImageResource(
            when(job.type) {
                JobType.COMICS -> R.drawable.ic_baseline_cruelty_free_24
                JobType.SERIES -> R.drawable.ic_baseline_computer_24
                JobType.EVENTS -> R.drawable.ic_baseline_sports_handball_24
                JobType.STORIES -> R.drawable.ic_baseline_auto_stories_24
            }
        )
        holder.binding.textCharacterName.text = job.items[position].name
    }

    override fun getItemCount(): Int = job.items.size


    inner class ViewHolder(val binding: CarouselDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}