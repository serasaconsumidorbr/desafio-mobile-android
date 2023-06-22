package com.example.marvel_characters.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface AdapterDelegate<T> {
    fun isForViewType(item: Any): Boolean
    fun onCreateViewHolder(parent: ViewGroup): ViewHolder
    fun onBindViewHolder(holder: ViewHolder, item: T)
    val diffUtil: DiffUtil.ItemCallback<Any>
}
