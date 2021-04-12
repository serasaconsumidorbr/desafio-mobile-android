package com.desafio.marvel.characters.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.desafio.marvel.DumbData
import com.desafio.marvel.feature.characters.data.datasource.CharactersDataSource
import com.desafio.marvel.feature.characters.data.datasource.CharactersLocalDataSource
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CharactersLocalDataSourceTest {

    private lateinit var mDataSource: CharactersDataSource

    lateinit var mPreferences: SharedPreferences

    @Mock
    private lateinit var mContext: Context

    @Mock
    private lateinit var sharedPrefs: SharedPreferences

    @Mock
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(mContext.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)
        `when`(sharedPrefs.edit()).thenReturn(sharedPrefsEditor)
        `when`(sharedPrefsEditor.putString(anyString(), anyString())).thenReturn(sharedPrefsEditor)
        `when`(sharedPrefsEditor.commit()).thenReturn(true)



        mPreferences = mContext.getSharedPreferences("User", 0)

        mDataSource = CharactersLocalDataSource(mPreferences)
    }

    @Test
    fun saveCharactersTest() {

        `when`(mDataSource.saveCharacters(DumbData.getCharactersResponse()))
            .thenReturn(true)

        verify(sharedPrefsEditor, times(1)).putString(anyString(), anyString())

        Assertions.assertThat(mDataSource.saveCharacters(DumbData.getCharactersResponse()))
            .isTrue()

    }

    @Test
    fun saveCharactersTest_error() {

        `when`(mDataSource.saveCharacters(DumbData.getCharactersResponse()))
            .thenReturn(false)

        verify(sharedPrefsEditor, times(1)).putString(anyString(), anyString())

        Assertions.assertThat(mDataSource.saveCharacters(DumbData.getCharactersResponse()))
            .isFalse()

    }
}