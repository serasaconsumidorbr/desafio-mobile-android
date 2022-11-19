package br.com.marvel.domain.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Thumbnail(
    @JsonProperty("path") val path: String, @JsonProperty("extension") val extension: String
) : Parcelable