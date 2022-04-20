package com.br.leandro.marvel_hero_app.domain.hero.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeroesListUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCaseConsumerProducer<Int, List<Hero>> {

    /**
     * @param param number of heroes request to the API
     */
    override fun execute(param: Int): Flow<ActionResult<List<Hero>>> {
        return flow {
            val heroes = heroRepository.getElementsFromApi(numberOfElements = param)
            heroes.collect { emit(it) }
        }
    }

    override val className: String
        get() = GetHeroesListUseCase::class.simpleName.orEmpty()
}