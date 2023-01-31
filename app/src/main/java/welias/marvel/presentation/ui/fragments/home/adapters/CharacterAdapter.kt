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
import welias.marvel.databinding.CharacterItemBinding
import welias.marvel.presentation.model.CharacterUI

class CharacterAdapter :
    ListAdapter<CharacterUI, CharacterViewHolder>(DiffCallback) {

    private var itemWithLoadingPosition: Int? = null
    private var newListSize: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(
            characterUI = getItem(position),
            shouldShowLoading = shouldShowLoading(position, newListSize)
        )
    }

    fun updateLoading(newListSize: Int) {
        itemWithLoadingPosition?.let { notifyItemChanged(it) }
        this.newListSize = newListSize
    }

    private fun shouldShowLoading(currentPosition: Int, newListSize: Int?): Boolean {
        return if (newListSize != null && (newListSize - 1) == currentPosition) {
            itemWithLoadingPosition = currentPosition
            true
        } else false
    }
}

class CharacterViewHolder(private val viewBinding: CharacterItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun onBind(characterUI: CharacterUI, shouldShowLoading: Boolean) {
        viewBinding.apply {
            name.text = characterUI.name
            description.text = characterUI.description
            loading.isVisible = shouldShowLoading

            Picasso.get()
                .load(characterUI.uri.checkImageAvailable())
                .into(
                    image,
                    object : Callback {
                        override fun onSuccess() {
                            initialImage.isVisible = false
                        }

                        override fun onError(e: Exception?) {
                            initialImage.isVisible = true
                        }
                    }
                )
        }
    }
}

private val DiffCallback = object : DiffUtil.ItemCallback<CharacterUI>() {
    override fun areItemsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
        return oldItem == newItem
    }
}
