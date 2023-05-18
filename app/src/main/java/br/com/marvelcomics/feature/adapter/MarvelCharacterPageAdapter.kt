package br.com.marvelcomics.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelcomics.databinding.AdapterCharItemViewBinding
import br.com.marvelcomics.model.MarvelCharacter
import com.bumptech.glide.Glide

class MarvelCharacterPageAdapter(
    private val onClick: (MarvelCharacter) -> Unit
) : PagingDataAdapter<MarvelCharacter, MarvelCharacterPageAdapter.MarvelCharacterViewHolder>(
    CHAR_DIFF
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCharItemViewBinding.inflate(inflater, parent, false)
        return MarvelCharacterViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarvelCharacterViewHolder(
        private val binding: AdapterCharItemViewBinding,
        private val onClick: (MarvelCharacter) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacter?) {
          item?.let { char ->
              with(binding) {
                  Glide.with(thumbnail).load(char.thumbnail).into(thumbnail)
                  name.text = char.name
                  description.text = char.description
              }
          }
        }
    }

    companion object {
        private val CHAR_DIFF = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}