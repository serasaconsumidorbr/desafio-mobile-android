package com.updeveloped.desafiomarvel.domain.entities

import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail

data class ListInformation(
    var count: Int? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var results: List<CharacterDetail>? = null,
    var total: Int? = null
)
