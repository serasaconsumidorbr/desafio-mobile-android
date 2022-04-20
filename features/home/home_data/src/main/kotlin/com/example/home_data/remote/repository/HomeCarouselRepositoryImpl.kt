package com.example.home_data.remote.repository

import com.example.home_data.remote.HomeApi
import com.example.home_data.remote.configs.CarouselConfig
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.repository.HomeCarouselRepository
import com.example.util.Resource
import com.example.util.api.ImageVariant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeCarouselRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val charactersDataDtoToCharacters: CharactersDataDtoToCharactersMapper,
    private val carouselConfig: CarouselConfig,
) : HomeCarouselRepository {

    override fun getHomeCarouselCharacters(): Flow<Resource<List<CharacterHomeUiModel>>> = flow {
        emit(value = Resource.Loading())
        loadFromRemote()
    }

    private suspend fun FlowCollector<Resource<List<CharacterHomeUiModel>>>.loadFromRemote() {
        val remoteCharacters = try {
            api.getCharacters(
                limit = carouselConfig.quantity,
                offset = carouselConfig.startIndex
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't load data"))
            null
        } catch (e: HttpException) {
            emit(Resource.Error("Couldn't load data"))
            null
        }
        remoteCharacters?.data?.let {
            val uiCharacters = charactersDataDtoToCharacters.mapFrom(
                dto = it,
                imageVariant = ImageVariant.Standard,
                imageType = ImageVariant.Type.MEDIUM
            )
            emit(
                Resource.Success(
                    data = uiCharacters
                )
            )
        }
    }
}