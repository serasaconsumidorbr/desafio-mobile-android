package welias.marvel.presentation.mapper

import welias.marvel.domain.model.CharacterDomain
import welias.marvel.domain.model.ThumbnailDomain
import welias.marvel.presentation.model.CharacterUI

fun CharacterDomain.toCharacterUi(): CharacterUI {
    return CharacterUI(
        id = this.id,
        name = this.name,
        description = this.description,
        uri = this.thumbnail.toUri()
    )
}

fun ThumbnailDomain.toUri(): String {
    return "${this.path}.${this.extension}"
}
