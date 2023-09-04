package com.example.marvel_app.features.detail.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app.databinding.ItemParentDetailBinding
import com.example.marvel_app.features.detail.presentation.adapter.DetailChildAdapter
import com.example.marvel_app.features.detail.response.DetailParentViewEntity
import com.example.marvel_app.framework.imageloader.ImageLoader

class DetailParentViewHolder(
    itemBinding: ItemParentDetailBinding,
    private val imageLoader: ImageLoader
): RecyclerView.ViewHolder(itemBinding.root) {

    private val textItemCategory: TextView = itemBinding.textItemCategory
    private val recyclerChildDetail: RecyclerView = itemBinding.recyclerChildDetail

    fun bind(detailParentViewEntity: DetailParentViewEntity){
        textItemCategory.text = itemView.context.getString(detailParentViewEntity.categoryStringResId)
        recyclerChildDetail.run {
            setHasFixedSize(true)
            adapter = DetailChildAdapter(detailParentViewEntity.detailChildList, imageLoader)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader
        ): DetailParentViewHolder {
            val itemBinding = ItemParentDetailBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return DetailParentViewHolder(itemBinding, imageLoader)
        }
    }
}