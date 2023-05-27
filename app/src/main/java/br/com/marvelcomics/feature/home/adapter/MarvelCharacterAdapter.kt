package br.com.marvelcomics.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelcomics.base.util.PageDataState
import br.com.marvelcomics.databinding.AdapterCharItemViewBinding
import br.com.marvelcomics.databinding.AdapterFeatureCharItemViewBinding
import br.com.marvelcomics.databinding.AdapterPageLoadErrorStateBinding
import br.com.marvelcomics.databinding.AdapterSeparatorBinding
import br.com.marvelcomics.model.MarvelCharacterEntry

class MarvelCharacterAdapter(
    private val onRetry: () -> Unit
) : RecyclerView.Adapter<MarvelCharacterPageViewHolder>() {

    private var items = mutableListOf<MarvelCharacterEntry>()

    companion object {
        private const val TITLE_VIEW_TYPE = 8
        private const val FEATURE_VIEW_TYPE = 9
        private const val DATA_VIEW_TYPE = 10
        private const val LOADING_VIEW_TYPE = 11
        private const val ERROR_VIEW_TYPE = 12
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelCharacterPageViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val viewHolder = when (viewType) {
            TITLE_VIEW_TYPE -> {
                val binding = AdapterSeparatorBinding.inflate(inflater, parent, false)
                TitleMarvelCharacterViewHolder(binding)
            }

            FEATURE_VIEW_TYPE -> {
                val binding = AdapterFeatureCharItemViewBinding.inflate(inflater, parent, false)
                FeatureEntryMarvelCharacterViewHolder(binding)
            }

            DATA_VIEW_TYPE -> {
                val binding = AdapterCharItemViewBinding.inflate(inflater, parent, false)
                DataMarvelCharacterViewHolder(binding)
            }

            LOADING_VIEW_TYPE -> {
                val binding = AdapterPageLoadErrorStateBinding.inflate(inflater, parent, false)
                LoadingMarvelCharacterViewHolder(binding)
            }

            ERROR_VIEW_TYPE -> {
                val binding = AdapterPageLoadErrorStateBinding.inflate(inflater, parent, false)
                ErrorMarvelCharacterViewHolder(binding, onRetry)
            }

            else -> throw IllegalArgumentException("View type inválido")
        }

        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when {
            item is MarvelCharacterEntry.FeatureItem -> FEATURE_VIEW_TYPE
            item is MarvelCharacterEntry.Title -> TITLE_VIEW_TYPE
            item is MarvelCharacterEntry.Item && item.item is PageDataState.Data -> DATA_VIEW_TYPE
            item is MarvelCharacterEntry.Item && item.item is PageDataState.Loading -> LOADING_VIEW_TYPE
            item is MarvelCharacterEntry.Item && item.item is PageDataState.Error -> ERROR_VIEW_TYPE
            else -> throw IllegalArgumentException("Tipo inválido")
        }
    }

    override fun onBindViewHolder(holder: MarvelCharacterPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitData(list: List<MarvelCharacterEntry>) {
        val diffCallback = MarvelCharacterItemDiffUtil(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    private fun showLoading() {
        items.add(MarvelCharacterEntry.Item(PageDataState.Loading()))
        notifyItemInserted(items.size - 1)
    }

    private fun hideLoading() {
        if (items.isEmpty()) return

        items.removeAll {
            it is MarvelCharacterEntry.Item && it.item is PageDataState.Loading
        }
        notifyItemRemoved(items.size)
    }

    fun handleError(isError: Boolean, errorMessage: String) {
        if (isError) showError(errorMessage) else hideError()
    }

    private fun showError(errorMessage: String) {
        items.add(MarvelCharacterEntry.Item(PageDataState.Error(errorMessage)))
        notifyItemInserted(items.size - 1)
    }

    private fun hideError() {
        if (items.isEmpty()) return

        items.removeAll {
            it is MarvelCharacterEntry.Item && it.item is PageDataState.Error
        }
        notifyItemRemoved(items.size)
    }


    class MarvelCharacterItemDiffUtil(
        private val oldList: List<MarvelCharacterEntry>,
        private val newList: List<MarvelCharacterEntry>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val newItem = newList[newItemPosition]
            val oldItem = oldList[oldItemPosition]

            return when {
                oldItem is MarvelCharacterEntry.FeatureItem && newItem is MarvelCharacterEntry.FeatureItem -> true
                oldItem is MarvelCharacterEntry.Title && newItem is MarvelCharacterEntry.Title -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Data && newItem.item is PageDataState.Data && oldItem.item.data.id == newItem.item.data.id -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Loading && newItem.item is PageDataState.Loading -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Error && newItem.item is PageDataState.Error -> true
                else -> false
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val newItem = newList[newItemPosition]
            val oldItem = oldList[oldItemPosition]

            return when {
                oldItem is MarvelCharacterEntry.FeatureItem && newItem is MarvelCharacterEntry.FeatureItem -> true
                oldItem is MarvelCharacterEntry.Title && newItem is MarvelCharacterEntry.Title && oldItem.title == newItem.title -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Data && newItem.item is PageDataState.Data && oldItem.item.data.id == newItem.item.data.id -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Loading && newItem.item is PageDataState.Loading -> true
                oldItem is MarvelCharacterEntry.Item && newItem is MarvelCharacterEntry.Item && oldItem.item is PageDataState.Error && newItem.item is PageDataState.Error && oldItem.item.message == newItem.item.message -> true
                else -> false
            }
        }
    }
}