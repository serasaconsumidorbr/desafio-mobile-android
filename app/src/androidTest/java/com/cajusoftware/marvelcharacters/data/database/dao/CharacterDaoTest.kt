package com.cajusoftware.marvelcharacters.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cajusoftware.marvelcharacters.data.database.MarvelDatabase
import com.cajusoftware.marvelcharacters.utils.asCharacterDto
import com.cajusoftware.tests.fakes.FakeData.characterDataWrapperResponse
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {

    private lateinit var characterDao: CharacterDao
    private lateinit var marvelDatabase: MarvelDatabase

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        marvelDatabase = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        characterDao = marvelDatabase.characterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        marvelDatabase.close()
    }

    @Test
    @Throws(IOException::class)
    fun daoInsertCharacters_insertCharactersIntoDatabase() = runBlocking {
        addAllCharactersOnDatabase()
        val character = characterDao.getCharacter(0)
        TestCase.assertEquals(
            characterDataWrapperResponse.data?.results!!.map {
                it.asCharacterDto(
                    characterDataWrapperResponse.copyright,
                    1
                )
            }.first(),
            character.first()
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoGetCharactersForPaging_returnCharactersList() = runBlocking {
        addAllCharactersOnDatabase()
        val character = characterDao.getCharactersForPaging(1)
        TestCase.assertEquals(
            characterDataWrapperResponse.data?.results!!.map {
                it.asCharacterDto(
                    characterDataWrapperResponse.copyright,
                    1
                )
            }.filter { it.id == 1 },
            character
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoGetCharactersRandomly_returnCharactersList() = runBlocking {
        addAllCharactersOnDatabase()
        val character = characterDao.getCharactersRandomly()

        val characterList = characterDataWrapperResponse.data?.results!!.map {
            it.asCharacterDto(
                characterDataWrapperResponse.copyright,
                1
            )
        }

        assert(
            character.contains(characterList.first()).and(character.contains(characterList.last()))
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoGetCount_returnCharactersListSize() = runBlocking {
        addAllCharactersOnDatabase()
        val size = characterDao.getCount()

        assert(size == 2)
    }

    private suspend fun addAllCharactersOnDatabase() {
        characterDao.insertCharacters(characterDataWrapperResponse.data?.results!!.map {
            it.asCharacterDto(characterDataWrapperResponse.copyright, 1)
        })
    }
}