package welias.marvel.presentation.ui.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import welias.marvel.core.extensions.checkImageAvailable
import welias.marvel.databinding.TopCharacterItemBinding
import welias.marvel.presentation.model.CharacterUI

class TopCharactersAdapter :
    ListAdapter<CharacterUI, TopViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(
            TopCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class TopViewHolder(private val viewBinding: TopCharacterItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(characterUI: CharacterUI) {
        viewBinding.apply {
            name.text = characterUI.name

            Picasso.get()
                .load(characterUI.uri.checkImageAvailable())
                .into(
                    image,
                    object : Callback {
                        override fun onSuccess() {
                            imageInitial.isVisible = false
                        }

                        override fun onError(e: Exception?) {
                            imageInitial.isVisible = true
                        }
                    }
                )
        }
    }
}

private val DiffCallback = object : DiffUtil.ItemCallback<CharacterUI>() {
    override fun areItemsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
        return oldItem.name == newItem.name
    }
}
