package com.example.marvel_characters.presentation.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvel_characters.core.adapter.AdapterDelegate
import com.example.marvel_characters.databinding.ItemTitleBinding
import com.example.marvel_characters.presentation.model.CharacterListItems

class TitleItemDelegate : AdapterDelegate<CharacterListItems.TitleModel> {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: CharacterListItems.TitleModel) {
        if (holder is TitleViewHolder) {
            holder.bind(item)
        }
    }

    override fun isForViewType(item: Any): Boolean {
        return item is CharacterListItems.TitleModel
    }

    class TitleViewHolder(
        private val binding: ItemTitleBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: CharacterListItems.TitleModel) {
            binding.itemTitle.text = binding.root.context.getString(item.textId)
        }
    }

    override val diffUtil = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (newItem !is CharacterListItems.TitleModel || oldItem !is CharacterListItems.TitleModel) {
                return false
            }
            return newItem.textId == oldItem.textId
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}