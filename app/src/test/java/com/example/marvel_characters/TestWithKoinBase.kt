package com.example.marvel_characters

import com.example.marvel_characters.di.appModule
import org.junit.After
import org.junit.Before
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

abstract class TestWithKoinBase
{
    @Before
    open fun setup() {
        startKoin { GlobalContext.loadKoinModules(appModule) }
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
    }

}