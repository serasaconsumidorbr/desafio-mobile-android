package br.com.marvel.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataResponse(
    @JsonProperty("data") val data: CharacterResultsResponse? = null
)
