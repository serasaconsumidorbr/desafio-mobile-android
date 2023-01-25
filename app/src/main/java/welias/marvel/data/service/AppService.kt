package welias.marvel.data.service

import retrofit2.http.GET
import retrofit2.http.Query
import welias.marvel.BuildConfig
import welias.marvel.core.extensions.generateMD5Hash
import welias.marvel.core.extensions.getTimeStamp
import welias.marvel.data.model.CharacterDataWrapperResponse

interface AppService {

    @GET("v1/public/characters")
    suspend fun getListCharacters(
        @Query("ts") ts: String = getTimeStamp(),
        @Query("apikey") apikey: String = BuildConfig.PUBLIC_KEY,
        @Query("hash") hash: String = generateMD5Hash(),
        @Query("offset") offset: Int? = 0
    ): CharacterDataWrapperResponse
}
