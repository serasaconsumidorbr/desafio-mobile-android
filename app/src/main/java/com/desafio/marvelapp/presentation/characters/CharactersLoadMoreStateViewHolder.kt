package com.desafio.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.desafio.marvelapp.databinding.ItemCharacterLoadMoreStateBinding
import com.desafio.marvelapp.databinding.ShimmerLayoutCharacterItemBinding

class CharactersLoadMoreStateViewHolder(
    itemBinding: ItemCharacterLoadMoreStateBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(itemBinding.root) {
    private val binding = ItemCharacterLoadMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgainMessage = binding.textTryAgain.also {
        it.setOnClickListener{
            retry()
        }
    }

    fun bind(loadState: LoadState){
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object{
        fun create(parent: ViewGroup, retry: () -> Unit):CharactersLoadMoreStateViewHolder{
            val itemBinding = ItemCharacterLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CharactersLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}