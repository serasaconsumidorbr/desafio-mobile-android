package com.desafio.marvel.characters.domain.interactor

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import com.nhaarman.mockitokotlin2.any
import com.desafio.marvel.DumbData
import com.desafio.marvel.feature.characters.data.datasource.CharactersDataSource
import com.desafio.marvel.feature.characters.data.repository.CharactersRepository
import com.desafio.marvel.feature.characters.domain.interactor.CharactersInteractor
import com.desafio.marvel.feature.characters.domain.interactor.CharactersInteractorImpl
import com.desafio.marvel.feature.characters.ui.presenter.CharactersPresenter
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CharactersInteractorTest {

    @Mock
    private lateinit var mCharactersRepository: CharactersRepository

    @Mock
    private lateinit var mCharactersPresenter: CharactersPresenter

    @Mock
    private lateinit var mCharactersDataSource: CharactersDataSource

    @Mock
    private lateinit var mContext: Context

    private lateinit var mCharactersInteractor: CharactersInteractor

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Mock
    private lateinit var activeNetwork: Network

    @Mock
    private lateinit var networkCapabilities: NetworkCapabilities

    @Mock
    private lateinit var activeNetworkInfo: NetworkInfo


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(mContext.getSystemService(any())).thenReturn(connectivityManager)
        `when`(connectivityManager.activeNetwork).thenReturn(activeNetwork)
        `when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(networkCapabilities)
        `when`(networkCapabilities.hasTransport(any())).thenReturn(true)

        mCharactersInteractor = CharactersInteractorImpl(mCharactersRepository, mCharactersPresenter, mCharactersDataSource, mContext)
    }

    @Test
    fun `When called getCharacters with status true and connection internet`() {

        `when`(connectivityManager.activeNetworkInfo).thenReturn(activeNetworkInfo)
        `when`(activeNetworkInfo.type).thenReturn(TYPE_WIFI)


        mCharactersInteractor.getCharacters(0)

        `when`(mCharactersDataSource.statusCharacters()).thenReturn(true)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(Unit)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(mCharactersPresenter.showCharacters(DumbData.getCharactersResponse()))
    }

    @Test
    fun `When called getCharacters with status false and connection internet`() {

        `when`(connectivityManager.activeNetworkInfo).thenReturn(activeNetworkInfo)
        `when`(activeNetworkInfo.type).thenReturn(TYPE_WIFI)


        `when`(mCharactersDataSource.statusCharacters()).thenReturn(false)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(Unit)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(mCharactersPresenter.showCharacters(DumbData.getCharactersResponse()))
    }

    @Test
    fun `When called getCharacters with status true and don't connection internet`() {

        mCharactersInteractor.getCharacters(0)

        `when`(mCharactersDataSource.statusCharacters()).thenReturn(true)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(Unit)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(mCharactersPresenter.showCharacters(DumbData.getCharactersResponse()))
    }

    @Test
    fun `When called getCharacters with status false and don't connection internet`() {

        `when`(mCharactersDataSource.statusCharacters()).thenReturn(false)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(Unit)

        Assertions.assertThat(mCharactersInteractor.getCharacters(0)).isEqualTo(mCharactersPresenter.showCharacters(DumbData.getCharactersResponse()))
    }


}