package com.example.marvel_characters.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class ListAdapterDelegate<T : Any>(
    delegates: List<AdapterDelegate<out T>>
) : ListAdapter<T, ViewHolder>(buildDiff(delegates)) {

    private val delegates: List<AdapterDelegate<Any>>

    init {
        @Suppress("UNCHECKED_CAST")
        this.delegates = delegates as List<AdapterDelegate<Any>>
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return delegates.indexOfFirst { it.isForViewType(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ViewHolder {
        if (index >= delegates.size) {
            throw Exception("Adapter delegate not registered")
        }
        return delegates[index].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val index = getItemViewType(position)

        if (index >= delegates.size) {
            throw Exception("Adapter delegate not registered")
        }

        delegates[index].onBindViewHolder(holder, getItem(position))
    }

    companion object {
        private fun <T : Any> buildDiff(delegates: List<AdapterDelegate<out T>>) =
            object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                    return delegates
                        .firstOrNull { it.isForViewType(newItem) }
                        ?.diffUtil
                        ?.areItemsTheSame(oldItem, newItem) == true
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return delegates
                        .firstOrNull { it.isForViewType(newItem) }
                        ?.diffUtil
                        ?.areContentsTheSame(oldItem, newItem) == true
                }
            }
    }
}