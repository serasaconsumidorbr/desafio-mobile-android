package br.com.marvelcomics.feature.home.adapter

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.marvelcomics.R
import br.com.marvelcomics.base.util.PageDataState
import br.com.marvelcomics.databinding.AdapterCharItemViewBinding
import br.com.marvelcomics.databinding.AdapterFeatureCharItemViewBinding
import br.com.marvelcomics.databinding.AdapterPageLoadErrorStateBinding
import br.com.marvelcomics.databinding.AdapterSeparatorBinding
import br.com.marvelcomics.model.MarvelCharacterEntry
import com.bumptech.glide.Glide

abstract class MarvelCharacterPageViewHolder(
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(entry: MarvelCharacterEntry)
}

class FeatureEntryMarvelCharacterViewHolder(
    private val binding: AdapterFeatureCharItemViewBinding,
) : MarvelCharacterPageViewHolder(binding) {

    private val featureAdapter : FeatureMarvelCharacterAdapter by lazy {
        FeatureMarvelCharacterAdapter()
    }

    override fun bind(entry: MarvelCharacterEntry) {
        val features = (entry as MarvelCharacterEntry.FeatureItem).list
        featureAdapter.items = features
        with(binding) {
            pagerContent.adapter = featureAdapter
        }
    }
}

class TitleMarvelCharacterViewHolder(
    private val binding: AdapterSeparatorBinding,
) : MarvelCharacterPageViewHolder(binding) {

    override fun bind(entry: MarvelCharacterEntry) {
        val title = (entry as MarvelCharacterEntry.Title).title
        with(binding) {
            separatorTitle.setText(title)
        }
    }
}

class DataMarvelCharacterViewHolder(
    private val binding: AdapterCharItemViewBinding
) : MarvelCharacterPageViewHolder(binding) {

    override fun bind(entry: MarvelCharacterEntry) {
        val marvelCharacter = ((entry as MarvelCharacterEntry.Item).item as PageDataState.Data).data
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
) : MarvelCharacterPageViewHolder(binding) {

    override fun bind(entry: MarvelCharacterEntry) {
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
) : MarvelCharacterPageViewHolder(binding) {

    override fun bind(entry: MarvelCharacterEntry) {
        with(binding) {
            pagingProgress.isGone = true
            btnTryAgain.isVisible = true
            pagingErrorMessage.isVisible = true
            btnTryAgain.setOnClickListener { onRetry.invoke() }
        }
    }
}