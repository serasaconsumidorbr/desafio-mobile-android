package com.br.leandro.marvel_hero_app.domain.hero

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Parcelize
@Entity(tableName = TABLE_NAME)
data class Hero(
    @PrimaryKey
    var id: Int? = 0,
    var name: String = "",
    var thumbnail: String = "",
    var extension: String = "",
    var description: String = ""
) : Parcelable {
    companion object {
        const val TABLE_NAME = "hero"
    }
}

fun Hero.imageUrl(orientation: ThumbnailOrientation, size: ThumbnailSize): String {
    var imageVariantUrl = ""
    imageVariantUrl += when (orientation) {
        ThumbnailOrientation.PORTRAIT -> {
            orientation.path
        }
        ThumbnailOrientation.LANDSCAPE -> {
            orientation.path
        }
    }
    imageVariantUrl += when (size) {
        ThumbnailSize.SMALL -> {
            size.path
        }
        ThumbnailSize.MEDIUM -> {
            size.path
        }
        ThumbnailSize.LARGE -> {
            size.path
        }
    }
    return thumbnail + imageVariantUrl + extension
}
