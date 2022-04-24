package dev.ribeiro.bruno.desafioandroid

import androidx.paging.PagingData
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GetAllCharactersUseCaseTest: KoinTest {

    private val getAllCharactersUseCase: GetAllCharactersUseCase by inject()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup(){
            configureTestAppComponent()
        }

        @AfterClass
        fun tearDown(){
            stopKoin()
        }
    }

    @Test
    fun should_return_value_not_null(){
        runBlocking {
            val result = getAllCharactersUseCase.execute()
            assertNotNull(result)
        }
    }

    @Test
    fun should_return_value_type_character(){
        runBlocking {
            val result = getAllCharactersUseCase.execute()
            assertTrue(result is Flow<PagingData<CharacterDetail>>)
        }
    }

}