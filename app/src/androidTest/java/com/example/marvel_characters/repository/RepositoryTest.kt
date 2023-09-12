package com.example.marvel_characters.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.marvel_characters.Result
import com.example.marvel_characters.Samples.characterAIMWithCompleteData
import com.example.marvel_characters.Samples.charactersWithNonRepeatedElements
import com.example.marvel_characters.database.CharacterDatabase
import com.example.marvel_characters.database.MarvelDao
import com.example.marvel_characters.database.NotFoundException
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.java.KoinJavaComponent
import java.io.IOException


class RepositoryTest {

    private lateinit var characterDao: MarvelDao
    private lateinit var db: CharacterDatabase

    private val repository: Repository by KoinJavaComponent.inject(
        Repository::class.java
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CharacterDatabase::class.java
        ).build()
        characterDao = db.marvelDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeCharacterAndRead() {
        runBlocking {
            characterAIMWithCompleteData.let {
                repository.saveCharacter(it)
                val savedCharacterById = repository.getSavedCharacter(it.id)
                assertThat(savedCharacterById, equalTo(Result.Success(it)))
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeCharacterAndDelete() {
        runBlocking {

            characterAIMWithCompleteData.let {
                repository.saveCharacter(it)
                repository.deleteCharacter(it)

                val savedCharacterById = repository.getSavedCharacter(it.id)

                val castingResult = try {
                    ((savedCharacterById as Result.Error).exception as NotFoundException)
                } catch (exception: Exception) {
                    exception
                }

                val returnsExpectedException = castingResult is NotFoundException

                Assert.assertTrue(returnsExpectedException)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnExpectedList() {
        runBlocking {

            charactersWithNonRepeatedElements.let {
                it.forEach {
                    repository.saveCharacter(it)
                }

                val savedCharactersList = repository.getSavedCharacterList()

                assertThat(
                    savedCharactersList,
                    equalTo(Result.Success(it))
                )
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeCharacterAndReadAfterUpdate() {
        runBlocking {

            characterAIMWithCompleteData.let {
                repository.saveCharacter(it)
                val sameCharacterWithModifiedName = it.copy(name = "ModifiedName")
                repository.updateCharacter(sameCharacterWithModifiedName)

                val savedCharacterById = repository.getSavedCharacter(it.id)

                assertThat(
                    savedCharacterById,
                    equalTo(Result.Success(sameCharacterWithModifiedName))
                )
            }
        }
    }

    @Test
    fun shouldReturnNonEmptyCharactersListResult(): Unit = runBlocking {

        val resultData = repository.getNextPage()
        Assert.assertTrue(resultData is Result.Success)
        Assert.assertTrue((resultData as Result.Success).data.isNotEmpty())

    }
}