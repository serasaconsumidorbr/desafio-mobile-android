package com.example.marvel_app.features.detail.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app.features.detail.presentation.viewholder.DetailChildViewHolder
import com.example.marvel_app.features.detail.response.DetailChildViewEntity
import com.example.marvel_app.framework.imageloader.ImageLoader

class DetailChildAdapter(
    private val detailChildList: List<DetailChildViewEntity>,
    private val imageLoader: ImageLoader
): RecyclerView.Adapter<DetailChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildViewHolder {
        return DetailChildViewHolder.create(parent,imageLoader)
    }

    override fun getItemCount() = detailChildList.size

    override fun onBindViewHolder(holder: DetailChildViewHolder, position: Int) {
        holder.bind(detailChildList[position])
    }
}