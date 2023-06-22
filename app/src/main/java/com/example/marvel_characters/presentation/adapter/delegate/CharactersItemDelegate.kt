package com.example.marvel_characters.presentation.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvel_characters.core.adapter.AdapterDelegate
import com.example.marvel_characters.databinding.ItemListCharacterBinding
import com.example.marvel_characters.presentation.adapter.CharacterAdapter
import com.example.marvel_characters.presentation.model.CharacterListItems

class CharactersItemDelegate(
    private val adapter: CharacterAdapter,
) : AdapterDelegate<CharacterListItems.CharacterListModel> {
    override fun isForViewType(item: Any): Boolean {
        return item is CharacterListItems.CharacterListModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = ItemListCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterItemViewHolder(binding, adapter)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        item: CharacterListItems.CharacterListModel
    ) {
        if (holder is CharacterItemViewHolder) {
            holder.bind(item)
        }
    }

    class CharacterItemViewHolder(
        private val binding: ItemListCharacterBinding,
        private val adapter: CharacterAdapter
    ) : ViewHolder(binding.root) {

        fun bind(model: CharacterListItems.CharacterListModel) {
            adapter.submitList(model.characters)
            binding.listCharacterItems.adapter = adapter
        }
    }

    override val diffUtil = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (newItem !is CharacterListItems.CarouselListModel || oldItem !is CharacterListItems.CarouselListModel) {
                return false
            }
            return newItem.characters.size == oldItem.characters.size
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}