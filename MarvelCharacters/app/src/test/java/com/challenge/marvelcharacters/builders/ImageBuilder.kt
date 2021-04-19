package com.challenge.marvelcharacters.builders


import com.challenge.marvelcharacters.model.Image

public class ImageBuilder{
    private var path: String = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"
    private var extension: String = "jpg"

    fun setPath(path : String) = apply {
        this.path = path
    }

    fun setExtension(extension : String) = apply {
        this.extension = extension
    }

    fun build() = Image(path,extension)

}