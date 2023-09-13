package com.example.marvelapp.features.comics.data.mapper

import com.example.marvelapp.features.comics.data.dto.ComicsDetails
import com.example.marvelapp.features.comics.data.model.ComicDetailModel

fun ComicsDetails.toComicDetailModel(): ComicDetailModel {
    val comicDetailModel = ComicDetailModel()
    comicDetailModel.id = this.id
    comicDetailModel.title = this.title
    comicDetailModel.pages = this.pageCount
    comicDetailModel.image = this.thumbnail.path.plus(".").plus(this.thumbnail.extension)
    return comicDetailModel
}