package br.com.maceda.marvel.data.model

import java.io.Serializable

data class CharacterData(
  val results: List<Character>
) : Serializable