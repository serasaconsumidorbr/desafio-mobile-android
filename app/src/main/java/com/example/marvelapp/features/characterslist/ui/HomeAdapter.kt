package com.example.marvelapp.features.characterslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemHomeBinding
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.utils.IMAGE_NOT_AVAILABLE
import com.example.marvelapp.utils.STRING_FINAL_PATH

class HomeAdapter(
    private var listener: HomeAdapterListener
) : PagingDataAdapter<CharacterDetails, HomeAdapter.ViewHolder>(
    HomeDiff
) {

    interface HomeAdapterListener {
        fun onItemClick(id: String)
    }

    inner class ViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterDetails, isLastItem: Boolean) {
            binding.apply {
                txtTitle.text = item.name
                txtDate.text = itemView.context.getString(
                    R.string.character_comics_number,
                    item.comics.available
                )
                txtDescription.text = itemView.context.getString(
                    R.string.character_series_number,
                    item.series.available
                )
                separator.isVisible = !isLastItem
                val image = item.thumbnail.path.plus(".${item.thumbnail.extension}")
                imgCape.load(image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }

                itemView.setOnClickListener {
                    listener.onItemClick(id = item.id)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isLastItem = false
        if (position == itemCount - 1) {
            isLastItem = true
        }

        val item = getItem(position)
        val substring = item?.thumbnail?.path?.substring(STRING_FINAL_PATH)
        if (substring == IMAGE_NOT_AVAILABLE) {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        } else {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        getItem(position)?.let { holder.bind(it, isLastItem) }
    }

    object HomeDiff : DiffUtil.ItemCallback<CharacterDetails>() {
        override fun areItemsTheSame(
            oldItem: CharacterDetails,
            newItem: CharacterDetails,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterDetails,
            newItem: CharacterDetails,
        ): Boolean {
            return oldItem == newItem
        }
    }
}