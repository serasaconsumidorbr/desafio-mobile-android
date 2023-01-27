package welias.marvel.data.service

import retrofit2.http.GET
import retrofit2.http.Query
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
        @Query(TIME_STAMP) ts: String = getTimeStamp(),
        @Query(API_KEY) apikey: String = BuildConfig.PUBLIC_KEY,
        @Query(HASH) hash: String = generateMD5Hash(),
        @Query(OFFSET) offset: Int? = 0
    ): CharacterDataWrapperResponse
}
