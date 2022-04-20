package com.br.leandro.marvel_hero_app.domain.hero.usecase

import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.domain.hero.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveHeroInDataBaseUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCaseConsumerProducer<Hero, Boolean> {
    override val className: String
        get() = SaveHeroInDataBaseUseCase::class.simpleName.orEmpty()

    override fun execute(param: Hero): Flow<ActionResult<Boolean>> {
        return flow {
            heroRepository.insertDataIntoRoom(param).collect { it ->
                emit(it)
            }
        }
    }
}