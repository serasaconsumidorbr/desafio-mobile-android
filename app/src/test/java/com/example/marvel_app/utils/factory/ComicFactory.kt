package com.example.marvel_app.utils.factory

import com.example.core.features.details.domain.Comic

class ComicFactory {

    fun create(comic: FakeComic) = when (comic) {
        FakeComic.FakeComic1 -> Comic(
            2211506,
            "http://fakecomigurl.jpg"
        )
    }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
    }
}