package com.br.leandro.marvel_hero_app.domain.hero.usecase

import com.br.leandro.marvel_hero_app.common.ActionResult
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.domain.hero.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesHeroesListUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCaseProducer<List<Hero>> {

    override fun execute(): Flow<ActionResult<List<Hero>>> {
        return flow {
            val heroes = heroRepository.getElementsFromDatabase()
            heroes.collect { emit(it) }
        }
    }

    override val className: String
        get() = GetFavoritesHeroesListUseCase::class.simpleName.orEmpty()
}