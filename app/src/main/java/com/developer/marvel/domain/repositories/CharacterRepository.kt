package com.developer.marvel.domain.repositories

import com.developer.marvel.domain.entities.Character
import com.developer.marvel.domain.failures.*
import com.developer.marvel.infrastructure.exceptions.*
import kotlin.jvm.Throws

interface CharacterRepository {

    @Throws(
        GenericFailure::class,
        MissingParameterFailure::class,
        MissingHashFailure::class,
        MissingTimestampFailure::class,
        MissingAPIKeyFailure::class,
        InvalidRefererFailure::class,
        InvalidHashFailure::class,
        InvalidCredentialsFailure::class,
        MethodNotAllowedFailure::class,
        ForbiddenFailure::class
    )
    suspend fun getCharacters(page: Int, limit: Int): List<Character>
}