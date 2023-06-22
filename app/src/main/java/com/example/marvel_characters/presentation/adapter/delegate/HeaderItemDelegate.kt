package com.example.marvel_characters.presentation.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvel_characters.core.adapter.AdapterDelegate
import com.example.marvel_characters.databinding.ItemListCharacterHeaderBinding
import com.example.marvel_characters.presentation.adapter.HeaderCharacterAdapter
import com.example.marvel_characters.presentation.model.CharacterListItems

class HeaderItemDelegate(
    private val adapter: HeaderCharacterAdapter,
) : AdapterDelegate<CharacterListItems.CarouselListModel> {
    override fun isForViewType(item: Any): Boolean {
        return item is CharacterListItems.CarouselListModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = ItemListCharacterHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderItemViewHolder(binding, adapter)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        item: CharacterListItems.CarouselListModel
    ) {
        if (holder is HeaderItemViewHolder) {
            holder.bind(item)
        }
    }

    class HeaderItemViewHolder(
        private val binding: ItemListCharacterHeaderBinding,
        private val adapter: HeaderCharacterAdapter
    ) : ViewHolder(binding.root) {

        fun bind(model: CharacterListItems.CarouselListModel) {
            adapter.submitList(model.characters)
            binding.listCharacterHeaderItems.adapter = adapter
        }
    }

    override val diffUtil = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (newItem !is CharacterListItems.CarouselListModel || oldItem !is CharacterListItems.CarouselListModel) {
                return false
            }
            return newItem.hashCode() == oldItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}