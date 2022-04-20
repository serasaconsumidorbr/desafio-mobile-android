package com.example.home_data.remote.datasource

import androidx.paging.PagingState
import com.example.home_data.remote.HomeApi
import com.example.home_data.remote.configs.HomePageConfig
import com.example.home_data.remote.datasource.offset.OffsetCalculator
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class HomeListDataSourceImpl @Inject constructor(
    private val api: HomeApi,
    private val mapper: CharactersDataDtoToCharactersMapper,
    private val homePageConfig: HomePageConfig,
    private val offsetCalculator: OffsetCalculator
) : HomeListDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterHomeUiModel> =
        try {
            val key = params.key ?: homePageConfig.startingIndex
            val prevKey = if (key == homePageConfig.startingIndex) {
                null
            } else {
                key - homePageConfig.incrementValue
            }
            val nextKey = key + homePageConfig.incrementValue
            val offset = offsetCalculator(key)
            val response = api.getCharacters(
                limit = homePageConfig.size,
                offset = offset
            )
            val charactersUi = mapper.mapFrom(
                dto = response.data,
                imageVariant = ImageVariant.Landscape,
                imageType = ImageVariant.Type.LARGE
            )
            LoadResult.Page(
                data = charactersUi,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, CharacterHomeUiModel>): Int? =
        state.anchorPosition
            ?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(
                    homePageConfig.incrementValue
                ) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(
                    homePageConfig.incrementValue
                )
            }
}