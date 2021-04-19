package com.challenge.marvelcharacters.view.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.insertHeaderItem
import com.challenge.marvelcharacters.builders.CharacterBuilder
import com.challenge.marvelcharacters.model.Character
import com.challenge.marvelcharacters.repository.CharacterRepository
import com.challenge.marvelcharacters.utils.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {

    private lateinit var vm: CharacterViewModel
    private lateinit var repository: CharacterRepository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()


    @Before
    fun setUp() {
        repository = Mockito.mock(CharacterRepository::class.java)
        vm = CharacterViewModel(repository)
    }



    @Test
    fun testGetCarouselImageUrl() {
        vm.loadFirstCharacters()
        Assert.assertEquals("", vm.getCarouselImageUrl(0))

        val list = ArrayList<Character>()
        list.add(CharacterBuilder().build())
        runBlockingTest {
            Mockito.`when`(repository.loadCharacters(5)).thenReturn(list.toList())
            vm.loadFirstCharacters()
            Assert.assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg", vm.getCarouselImageUrl(0))
        }
    }

    @Test
    fun testLoadCharacter() {
        val list = ArrayList<Character>()
        list.add(CharacterBuilder().build())
        runBlockingTest {
            Mockito.`when`(repository.loadCharacters(5)).thenReturn(list.toList())
            vm.loadFirstCharacters()
            Assert.assertEquals(1, vm.carouselImageList.value?.size)
        }

    }

    @Test
    fun testGetCharacters() {

        val list = ArrayList<Character>()
        list.add(CharacterBuilder().build())
        val response = PagingData.from(list)
        var result =  flow<PagingData<Character>> { response }

        runBlockingTest {
            Mockito.`when`(repository.getCharacters()).thenReturn(result)
            Assert.assertNotNull(vm.getCharacters())
        }

    }

}