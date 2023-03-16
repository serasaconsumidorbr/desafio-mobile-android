package com.cajusoftware.marvelcharacters.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.cajusoftware.marvelcharacters.R
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.databinding.ItemCarouselCharacterBinding

class CharacterListAdapter(private val onItemClickListener: ((Int) -> Unit)) :
    PagingDataAdapter<CarouselCharacter, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCarouselCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun stateFlow(stateCallback: (CombinedLoadStates) -> Unit) {
        addLoadStateListener(stateCallback)
    }

    inner class CharacterViewHolder(private val binding: ItemCarouselCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CarouselCharacter) {
            binding.apply {
                characterName.text = character.name
                characterDescription.text = character.copyright

                characterImage.load(character.thumbnail?.thumbnailUri) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    diskCachePolicy(CachePolicy.ENABLED)
                }

                comicsAmount.text = character.comicsAmount?.toString()
                eventsAmount.text = character.eventsAmount?.toString()
                seriesAmount.text = character.seriesAmount?.toString()
                storiesAmount.text = character.storiesAmount?.toString()

                itemRoot.setOnClickListener { onItemClickListener(character.id) }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CarouselCharacter>() {
        override fun areItemsTheSame(
            oldItem: CarouselCharacter,
            newItem: CarouselCharacter
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CarouselCharacter,
            newItem: CarouselCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }
}