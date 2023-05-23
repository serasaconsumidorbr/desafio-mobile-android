package com.example.testeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testeapp.R
import com.example.testeapp.databinding.ItemCharacterBinding
import com.example.testeapp.model.MarvelCharacter

class CharacterAdapter(private val onCharacterItemClickListener: OnCharacterItemClickListener?) : ListAdapter<MarvelCharacter, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(marvelCharacter: MarvelCharacter) {
            binding.titleTextView.text = marvelCharacter.name
            binding.description.text = binding.root.context.getString(R.string.description, marvelCharacter.description.let { if(!it.isEmpty()) it else "N/A" })
            binding.favoriteButton.setImageResource(if (marvelCharacter.isFavorite) R.drawable.filled_star else R.drawable.star_outlined)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.favoriteButton.visibility = if (onCharacterItemClickListener != null) View.VISIBLE else View.GONE
        holder.binding.favoriteButton.setOnClickListener {
            item.isFavorite = !item.isFavorite
            holder.binding.favoriteButton.setImageResource(if (item.isFavorite) R.drawable.filled_star else R.drawable.star_outlined)
            onCharacterItemClickListener?.onFavoriteClicked(item)
        }


        holder.binding.apply {
            if(item.description.isNullOrEmpty())
                expandButton.visibility = View.GONE

            description.post {
                val layout = description.layout
                val ellipsisCount = layout?.getEllipsisCount(layout.lineCount - 1) ?: 0
                val isMoreThanThreeLines = ellipsisCount > 0
                expandButton.visibility = if (isMoreThanThreeLines) View.VISIBLE else View.GONE
            }

            expandButton.setOnClickListener {
                if (description.maxLines == 3) {
                    description.maxLines = Integer.MAX_VALUE
                    expandButton.text = holder.binding.root.context.getString(R.string.see_less)
                } else {
                    description.maxLines = 3
                    expandButton.text = holder.binding.root.context.getString(R.string.see_more)
                }
            }
        }


        holder.itemView.setOnLongClickListener {
            val newList = currentList.toMutableList().apply {
                removeAt(holder.adapterPosition)
            }
            submitList(newList)

            true
        }

        Glide.with(holder.itemView.context)
            .load(item.thumbnail.path + "." + item.thumbnail.extension)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.image)

    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem == newItem
        }
    }

}

interface OnCharacterItemClickListener {
    fun onFavoriteClicked(character: MarvelCharacter)
}