package com.example.marvel_app.features.detail.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app.databinding.ItemChildDetailBinding
import com.example.marvel_app.features.detail.response.DetailChildViewEntity
import com.example.marvel_app.framework.imageloader.ImageLoader
import kotlinx.coroutines.NonDisposableHandle.parent

class DetailChildViewHolder(
    itemBinding: ItemChildDetailBinding,
    private val imageLoader: ImageLoader
): RecyclerView.ViewHolder(itemBinding.root) {

    private val imageCategory: ImageView = itemBinding.imageItemCategory

    fun bind(detailChildViewEntity: DetailChildViewEntity) {
        imageLoader.load(imageCategory, detailChildViewEntity.imageUrl)
    }
    
    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader
        ): DetailChildViewHolder {
            val itemBinding = ItemChildDetailBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return DetailChildViewHolder(itemBinding, imageLoader)
        }
    }
}