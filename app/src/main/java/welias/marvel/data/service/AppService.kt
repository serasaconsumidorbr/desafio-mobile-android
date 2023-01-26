package welias.marvel.data.service

import retrofit2.http.GET
import retrofit2.http.Header
import welias.marvel.BuildConfig
import welias.marvel.core.constants.API_KEY
import welias.marvel.core.constants.HASH
import welias.marvel.core.constants.OFFSET
import welias.marvel.core.constants.TIME_STAMP
import welias.marvel.core.extensions.generateMD5Hash
import welias.marvel.core.extensions.getTimeStamp
import welias.marvel.data.model.CharacterDataWrapperResponse

interface AppService {

    @GET("v1/public/characters")
    suspend fun getListCharacters(
        @Header(TIME_STAMP) ts: String = getTimeStamp(),
        @Header(API_KEY) apikey: String = BuildConfig.PUBLIC_KEY,
        @Header(HASH) hash: String = generateMD5Hash(),
        @Header(OFFSET) offset: Int? = 0
    ): CharacterDataWrapperResponse
}
