package br.com.marvelcomics.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.marvelcomics.R
import br.com.marvelcomics.base.util.PageDataState
import br.com.marvelcomics.databinding.AdapterCharItemViewBinding
import br.com.marvelcomics.databinding.AdapterPageLoadErrorStateBinding
import br.com.marvelcomics.model.MarvelCharacter
import com.bumptech.glide.Glide

class MarvelCharacterAdapter(
    private val onRetry: () -> Unit
) : RecyclerView.Adapter<MarvelCharacterAdapter.MarvelCharacterViewHolder<MarvelCharacter>>() {

    private var items = mutableListOf<PageDataState<MarvelCharacter>>()

    companion object {
        private const val DATA_VIEW_TYPE = 10
        private const val LOADING_VIEW_TYPE = 11
        private const val ERROR_VIEW_TYPE = 12
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelCharacterViewHolder<MarvelCharacter> {
        val inflater = LayoutInflater.from(parent.context)

        val viewHolder = when (viewType) {
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
        return when (items[position]) {
            is PageDataState.Data -> DATA_VIEW_TYPE
            is PageDataState.Loading -> LOADING_VIEW_TYPE
            is PageDataState.Error -> ERROR_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder<MarvelCharacter>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitData(list: List<MarvelCharacter>) {
        items.clear()
        items.addAll(list.map { PageDataState.Data(it) })
        notifyItemInserted(items.size - 1)
    }

    fun handleLoading(isLoading : Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    fun handleError(isError : Boolean) {
        if (isError) showError() else hideError()
    }

    private fun showLoading() {
        items.add(PageDataState.Loading())
        notifyItemInserted(items.size - 1)
    }

    private fun hideLoading() {
        if (items.isNotEmpty() && items.last() is PageDataState.Loading) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    private fun showError() {
        items.add(PageDataState.Error())
        notifyItemInserted(items.size - 1)
    }

    private fun hideError() {
        if (items.isNotEmpty() && items.last() is PageDataState.Error) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    abstract class MarvelCharacterViewHolder<MarvelCharacter>(
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(dataState: PageDataState<MarvelCharacter>)
    }

    class DataMarvelCharacterViewHolder(private val binding: AdapterCharItemViewBinding) :
        MarvelCharacterViewHolder<MarvelCharacter>(binding) {

        override fun bind(dataState: PageDataState<MarvelCharacter>) {
            val marvelCharacter = (dataState as PageDataState.Data).data
            with(binding) {
                Glide.with(thumbnail).load(marvelCharacter.thumbnail).into(thumbnail)
                name.text = marvelCharacter.name
                description.text = marvelCharacter.description.ifEmpty {
                    itemView.context.getString(R.string.no_description)
                }
            }
        }
    }

    class LoadingMarvelCharacterViewHolder(
        private val binding: AdapterPageLoadErrorStateBinding
    ) : MarvelCharacterViewHolder<MarvelCharacter>(binding) {

        override fun bind(dataState: PageDataState<MarvelCharacter>) {
            with(binding) {
                pagingProgress.isVisible = true
                btnTryAgain.isGone = true
                pagingErrorMessage.isGone = true
            }
        }
    }

    class ErrorMarvelCharacterViewHolder(
        private val binding: AdapterPageLoadErrorStateBinding,
        private val onRetry: () -> Unit
    ) : MarvelCharacterViewHolder<MarvelCharacter>(binding) {

        override fun bind(dataState: PageDataState<MarvelCharacter>) {
            with(binding) {
                pagingProgress.isGone = true
                btnTryAgain.isVisible = true
                pagingErrorMessage.isVisible = true
                btnTryAgain.setOnClickListener { onRetry.invoke() }
            }
        }
    }
}