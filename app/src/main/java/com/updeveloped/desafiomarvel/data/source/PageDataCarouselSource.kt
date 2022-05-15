package com.updeveloped.desafiomarvel.data.source

import com.updeveloped.desafiomarvel.core.API_KEY_PUBLICA
import com.updeveloped.desafiomarvel.core.HASH
import com.updeveloped.desafiomarvel.helper.getMd5Digest
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.data.service.MarvelService
import com.updeveloped.desafiomarvel.data.toEntity

class PageDataCarouselSource(private val marvelService: MarvelService) {
    suspend fun load(): List<CharacterDetail> {
        val timestamp = System.currentTimeMillis().toInt()
        val md5Hash = "$timestamp$HASH$API_KEY_PUBLICA".getMd5Digest()

        val response = marvelService.getCharacters(timeStamp = timestamp, hashMd5 = md5Hash, limit = 5).toEntity()
        return response.results!!
    }
}
