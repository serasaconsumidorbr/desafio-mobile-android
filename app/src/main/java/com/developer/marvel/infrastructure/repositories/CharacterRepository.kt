package com.developer.marvel.infrastructure.repositories

import com.developer.marvel.domain.entities.Character
import com.developer.marvel.domain.failures.*
import com.developer.marvel.domain.repositories.CharacterRepository
import com.developer.marvel.infrastructure.datasources.CharacterDataSource
import com.developer.marvel.infrastructure.exceptions.*

class CharacterRepositoryImpl(
    private val dataSource: CharacterDataSource
) : CharacterRepository {

    override suspend fun getCharacters(page: Int, limit: Int): List<Character> {
        val characters = try {

            dataSource.getCharacters(page, limit)

        } catch (e: EmptyParameterException) {
            throw EmptyParameterFailure("Parametros estão vazios")
        } catch (e: ForbiddenException) {
            throw ForbiddenFailure("A ação não pode ser executada pois infringe algumas politicas")
        } catch (e: InvalidHashException) {
            throw InvalidHashFailure("A identificação usada está inválida")
        } catch (e: InvalidRefererException) {
            throw InvalidRefererFailure("A identificação usada está inválida")
        } catch (e: MethodNotAllowedException) {
            throw MethodNotAllowedFailure("O servidor não reconhece esse tipo de chamada")
        } catch (e: MissingHashException) {
            throw MissingHashFailure("Parametro de identificação não foi enviado")
        } catch (e: MissingParameterException) {
            throw MissingParameterFailure("Parametro de identificação não foi enviado")
        } catch (e: MissingTimestampException) {
            throw MissingTimestampFailure("Parametro de identificação não foi enviado")
        } catch (e: InvalidCredentialsException) {
            throw InvalidCredentialsFailure("Sua credencial está inválida neste momento, tente novamente")
        }

        return characters.map { it.mapperToEntity() }
    }

}