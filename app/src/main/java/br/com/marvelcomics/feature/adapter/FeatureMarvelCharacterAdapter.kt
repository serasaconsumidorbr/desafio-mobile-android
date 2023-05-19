package br.com.marvelcomics.feature.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelcomics.databinding.AdapterCarrouselCharItemViewBinding
import br.com.marvelcomics.model.MarvelCharacter
import com.bumptech.glide.Glide

class FeatureMarvelCharacterAdapter : RecyclerView.Adapter<FeatureMarvelCharacterAdapter.FeatureMarvelCharacterViewHolder>() {
    var items = emptyList<MarvelCharacter>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureMarvelCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCarrouselCharItemViewBinding.inflate(inflater, parent, false)
        return FeatureMarvelCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeatureMarvelCharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class FeatureMarvelCharacterViewHolder(
        private val binding: AdapterCarrouselCharItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(marvelCharacter: MarvelCharacter) {
            with(binding) {
                Glide.with(image).load(marvelCharacter.thumbnail).into(image)
                name.text = marvelCharacter.name
            }
        }
    }
}