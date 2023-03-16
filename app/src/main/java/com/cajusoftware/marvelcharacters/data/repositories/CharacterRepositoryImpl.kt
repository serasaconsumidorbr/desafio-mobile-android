package com.cajusoftware.marvelcharacters.data.repositories

import androidx.paging.*
import com.cajusoftware.marvelcharacters.BuildConfig.PAGE_PREFETCH_DISTANCE
import com.cajusoftware.marvelcharacters.BuildConfig.PAGE_SIZE
import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.sources.ModelsPagingMediator
import com.cajusoftware.marvelcharacters.data.database.sources.ModelsPagingSource
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.data.domain.Character
import com.cajusoftware.marvelcharacters.utils.asCarouselCharacter
import com.cajusoftware.marvelcharacters.utils.asCharacter
import com.cajusoftware.marvelcharacters.utils.toCarouselCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val characterDao: CharacterDao,
    remoteMediator: ModelsPagingMediator
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    private val pager = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PAGE_PREFETCH_DISTANCE),
        remoteMediator = remoteMediator,
    ) {
        ModelsPagingSource(characterDao).apply {
            remoteMediator.invalidatePagingSourceCallback = { invalidate() }
        }
    }

    override val carouselCharacters: Flow<PagingData<CarouselCharacter>>
        get() = pager.flow.toCarouselCharacter()

    private val _upperCarouselCharacters: MutableStateFlow<List<CarouselCharacter>> =
        MutableStateFlow(listOf())
    override val upperCarouselCharacters: Flow<List<CarouselCharacter>>
        get() = _upperCarouselCharacters

    private val _character: MutableStateFlow<Character?> = MutableStateFlow(null)
    override val character: Flow<Character?>
        get() = _character

    override suspend fun getCharactersRandomly() {
        withContext(Dispatchers.IO) {
            val characters = characterDao.getCharactersRandomly().map { it.asCarouselCharacter() }
            _upperCarouselCharacters.emit(characters)
        }
    }

    override suspend fun fetchCharacter(characterId: Int) {
        withContext(Dispatchers.IO) {
            val marvelCharacter = characterDao.getCharacter(characterId).map { it?.asCharacter() }
            _character.emit(marvelCharacter.first())
        }
    }
}