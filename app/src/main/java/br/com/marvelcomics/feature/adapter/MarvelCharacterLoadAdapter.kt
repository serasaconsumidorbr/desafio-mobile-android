package br.com.marvelcomics.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelcomics.databinding.AdapterPageLoadStateBinding

class MarvelCharacterLoadAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MarvelCharacterLoadAdapter.LoadStateViewHolder>() {


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = AdapterPageLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return LoadStateViewHolder(binding, retry)
    }

    class LoadStateViewHolder(
        private val binding: AdapterPageLoadStateBinding,
        private val retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnTryAgain.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                btnTryAgain.isVisible = loadState !is LoadState.Loading
                errorMessage.isVisible = loadState !is LoadState.Loading
                progress.isVisible = loadState is LoadState.Loading

//                if(loadState is LoadState.Error) {
//                    if (loadState.error is UIException.NoInternetException) {
//                        errorMessage.text =  root.context.getString(R.string.no_connection)
//                    } else {
//                        errorMessage.text =  root.context.getString(R.string.error_more_data)
//                    }
//                }
            }
        }
    }
}
