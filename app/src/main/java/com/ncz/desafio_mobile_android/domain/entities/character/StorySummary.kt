package com.ncz.desafio_mobile_android.domain.entities.character

import java.io.Serializable

data class StorySummary(
  val resourceURI: String,
  val name: String,
  val type: String,
 ): Serializable
