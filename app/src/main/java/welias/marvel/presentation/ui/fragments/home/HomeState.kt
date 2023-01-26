package welias.marvel.presentation.ui.fragments.home

import welias.marvel.core.constants.INITIAL_OFFSET
import welias.marvel.core.exception.ErrorException
import welias.marvel.presentation.model.CharacterUI

data class HomeState(
    val isLoading: Boolean = false,
    val isFirstRequisition: Boolean = true,
    val dataApi: DataApi = DataApi(),
    val listTopCharacters: List<CharacterUI>? = null,
    val characters: List<CharacterUI>? = null,
    val error: ErrorException? = null
)

data class DataApi(
    val nextOffset: Int = INITIAL_OFFSET,
    val totalItems: Int? = null
)
