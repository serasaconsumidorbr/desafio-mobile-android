package br.com.marvelcomics.base.extensions

import android.widget.ImageView
import br.com.marvelcomics.base.util.PageDataState
import br.com.marvelcomics.model.MarvelCharacter
import br.com.marvelcomics.model.MarvelCharacterEntry
import com.bumptech.glide.Glide
import retrofit2.Retrofit

inline fun <reified T> resolveRetrofit(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun List<MarvelCharacter>.asMarvelCharacterEntry(): List<MarvelCharacterEntry> {
    return this.map { MarvelCharacterEntry.Item(PageDataState.Data(it)) }
}

fun List<MarvelCharacter>.asMarvelCharacterEntryWithFeatures(
    featureTitle: MarvelCharacterEntry.Title,
    characterTitle: MarvelCharacterEntry.Title,
): MutableList<MarvelCharacterEntry> {
    val entryList = mutableListOf<MarvelCharacterEntry>()

    val featureList = this.take(5)
    val characterList = this.drop(5)
    entryList.add(featureTitle)
    entryList.add(MarvelCharacterEntry.FeatureItem(featureList))
    entryList.add(characterTitle)
    characterList.asMarvelCharacterEntry().forEach { entryList.add(it) }

    return entryList
}

fun ImageView.loadCharacterThumbnail(thumbnail: String) {
    if (this.scaleType == ImageView.ScaleType.FIT_XY) {
        Glide.with(this).load(thumbnail).into(this)
    } else {
        Glide.with(this).load(thumbnail).circleCrop().into(this)
    }
}