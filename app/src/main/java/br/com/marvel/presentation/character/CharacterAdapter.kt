package br.com.marvel.presentation.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.databinding.CharacterItemViewBinding
import br.com.marvel.domain.models.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CharacterAdapter :
    PagingDataAdapter<Character, CharacterAdapter.ViewHolder>(CHARACTER_COMPARATOR) {

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(private val binding: CharacterItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {

            binding.apply {
                Glide.with(itemView)
                    .load("${character.thumbnail.path}.${character.thumbnail.extension}")
                    .centerCrop().error(android.R.drawable.stat_notify_error)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(thumbnail)

                name.text = character.name
            }
        }
    }

}