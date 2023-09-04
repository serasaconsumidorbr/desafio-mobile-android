package com.example.marvel_app.features.detail.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app.features.detail.presentation.viewholder.DetailParentViewHolder
import com.example.marvel_app.features.detail.response.DetailParentViewEntity
import com.example.marvel_app.framework.imageloader.ImageLoader

class DetailParentAdapter(
    private val detailParentList: List<DetailParentViewEntity>,
    private val imageLoader: ImageLoader
): RecyclerView.Adapter<DetailParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailParentViewHolder {
        return DetailParentViewHolder.create(parent, imageLoader)
    }

    override fun getItemCount() = detailParentList.size

    override fun onBindViewHolder(holder: DetailParentViewHolder, position: Int) {
        holder.bind(detailParentList[position])
    }
}