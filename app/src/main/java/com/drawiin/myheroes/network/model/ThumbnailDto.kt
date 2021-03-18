package com.drawiin.myheroes.network.model


import com.drawiin.myheroes.domain.model.character.Thumbnail
import com.drawiin.myheroes.domain.util.FromDomainObject
import com.drawiin.myheroes.domain.util.ToDomainObject
import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
) : ToDomainObject<Thumbnail> {
    override fun toDomainObject() = Thumbnail(
        extension = extension,
        path = path
    )

    companion object: FromDomainObject<ThumbnailDto, Thumbnail> {
        override fun fromDomainObject(domainModel: Thumbnail) = ThumbnailDto(
            extension = domainModel.extension,
            path = domainModel.path
        )
    }
}