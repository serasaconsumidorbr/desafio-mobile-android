package com.example.marvel_characters.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.marvel_characters.Result
import com.example.marvel_characters.di.appModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent


class RepositoryTest {
    private val repository: Repository by KoinJavaComponent.inject(
        Repository::class.java
    )

   
    @Test
    fun shouldReturnNonEmptyCharactersListResult(): Unit = runBlocking {

        val resultData = repository.getCharactersFromWeb()
        Assert.assertTrue(resultData is Result.Success)
        Assert.assertTrue((resultData as Result.Success).data.isNotEmpty())

    }
}