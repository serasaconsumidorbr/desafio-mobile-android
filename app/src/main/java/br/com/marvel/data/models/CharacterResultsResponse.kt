package br.com.marvel.data.models

import android.os.Parcelable
import br.com.marvel.domain.models.Character
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class CharacterResultsResponse(
    @JsonProperty("results") val results: List<Character> = emptyList()
) : Parcelable
