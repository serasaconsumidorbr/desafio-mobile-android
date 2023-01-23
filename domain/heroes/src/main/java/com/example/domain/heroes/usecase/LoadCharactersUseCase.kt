package com.example.domain.heroes.usecase


import com.example.domain.heroes.model.Page
import com.example.domain.heroes.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoadCharactersUseCase @Inject constructor(private val repository: HeroRepository) {
    operator fun invoke(page : Int) : Flow<Page> = repository.getHeroes(page)
}