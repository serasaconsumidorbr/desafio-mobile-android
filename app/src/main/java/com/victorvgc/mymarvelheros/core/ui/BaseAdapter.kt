package com.victorvgc.mymarvelheros.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.mymarvelheros.core.utils.setOnceClickListener

open class BaseAdapter<T>(
    @LayoutRes val layoutId: Int,
    private val itemCallback: DiffUtil.ItemCallback<T>,
    private val listener: ((T, position: Int) -> Unit)? = null
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    protected val itemList = mutableListOf<T>()

    inner class BaseDiffCallback(
        private val oldList: List<T>,
        private val newList: List<T>,
        private val itemCallback: ItemCallback<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return if (oldItem != null && newItem != null) {
                itemCallback.areItemsTheSame(oldItem, newItem)
            } else {
                oldItem == null && newItem == null
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return if (oldItem != null && newItem != null) {
                itemCallback.areContentsTheSame(oldItem, newItem)
            } else {
                oldItem == null && newItem == null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId, parent, false
        )
        return BaseViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(itemList[position], position)

    fun setupItems(items: List<T>) {
        val diffCallback = BaseDiffCallback(itemList, items, itemCallback)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }

    open class BaseViewHolder<T>(
        private val binding: ViewDataBinding,
        private val listener: ((T, position: Int) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(item: T, position: Int) {
            binding.setVariable(BR.item, item)
            itemView.setOnceClickListener { listener?.invoke(item, position) }
            binding.executePendingBindings()
        }
    }
}