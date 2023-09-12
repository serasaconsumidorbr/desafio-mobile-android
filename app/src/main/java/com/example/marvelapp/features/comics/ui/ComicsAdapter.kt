package com.example.marvelapp.features.comics.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemComicBinding
import com.example.marvelapp.features.comics.data.model.ComicDetailModel

class ComicsAdapter : PagingDataAdapter<ComicDetailModel, ComicsAdapter.ViewHolder>(
    ComicDiff
) {

    inner class ViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ComicDetailModel) {
            binding.apply {
                txtComicTitle.text = item.title
                txtComicPages.text =
                    itemView.context.getString(R.string.comic_page_number, item.pages)
                imgComic.load(item.image) {
                    crossfade(true)
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
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    object ComicDiff : DiffUtil.ItemCallback<ComicDetailModel>() {
        override fun areItemsTheSame(
            oldItem: ComicDetailModel,
            newItem: ComicDetailModel,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ComicDetailModel,
            newItem: ComicDetailModel,
        ): Boolean {
            return oldItem == newItem
        }
    }
}