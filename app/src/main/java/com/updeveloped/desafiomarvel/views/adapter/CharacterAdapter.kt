package com.updeveloped.desafiomarvel.views.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.views.viewHolder.CharacterItemViewHolder

class CharacterAdapter(
    val passItem: (CharacterDetail) -> (Unit)
) : PagingDataAdapter<CharacterDetail, CharacterItemViewHolder>(CharacterDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            passItem(item)
        }
    }

    object CharacterDifferentiator : DiffUtil.ItemCallback<CharacterDetail>() {

        override fun areItemsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem == newItem
        }
    }
}
