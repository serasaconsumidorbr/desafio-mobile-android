package com.example.data.characters

import app.cash.turbine.test
import com.example.data.heroes.HeroRepositoryImpl
import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.mapper.toEntity
import com.example.data.heroes.remote.HeroesApi
import com.example.data.heroes.remote.model.Data
import com.example.data.heroes.remote.model.ResponseApi
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.model.Page
import com.example.domain.heroes.model.Thumbnail
import com.example.domain.heroes.repository.HeroRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


private val mockWebServer = MockWebServer()
private lateinit var repository: HeroRepository

@RunWith(JUnit4::class)
class HeroRepositoryTest {

    private val listHeroes = listOf(
        Hero(
            1,
            "Kate Bishop",
            "",
            "2014-04-29T14:18:17-0400",
            Thumbnail("", ""),
            ""
        )
    )


    @Before
    fun setup() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val api = retrofit.create(HeroesApi::class.java)

        val dao: HeroDao = mock()


        dao.stub {
            onBlocking { getPagedList(20) }.thenReturn(
                listHeroes.toEntity()
            )
            onBlocking { getAll() }.thenReturn(
                listHeroes.toEntity()
            )
            onBlocking {
                listHeroes.forEach {
                    insert(it.toEntity())
                }
            }.thenReturn(Unit)
        }

        repository = HeroRepositoryImpl(api, dao)

    }

    @Test
    fun `Should return heroes list cache when server error`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        val flow: Flow<Page> = repository.getHeroes(0)
        flow.test {
            assert(awaitItem().hero.size == 1)
            awaitComplete()
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `Should return heroes list from server`() = runBlocking {
        val response = ResponseApi(200, "", "", "", "", "", Data(0, 20, 1500, 20, listHeroes))
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(response)))

        val flow: Flow<Page> = repository.getHeroes(0)
        flow.test {
            assert(awaitItem().hero.size == 1)
            awaitComplete()
        }
    }
}