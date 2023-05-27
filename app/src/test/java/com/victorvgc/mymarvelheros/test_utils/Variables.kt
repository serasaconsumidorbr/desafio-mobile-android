package com.victorvgc.mymarvelheros.test_utils

import com.victorvgc.mymarvelheros.home.data.model.local.LocalHero
import com.victorvgc.mymarvelheros.home.data.model.remote.RemoteHero
import com.victorvgc.mymarvelheros.home.data.model.remote.RemoteHeroPage
import com.victorvgc.mymarvelheros.home.data.model.remote.RemoteHeroResponse
import com.victorvgc.mymarvelheros.home.data.model.remote.RemoteImageResource
import com.victorvgc.mymarvelheros.home.data.utils.INCREDIBLE_LANDSCAPE
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

const val testHeroId = 1
const val testHeroName = "Super Tester"
const val testHeroImgUrl = "img_url"
const val testHeroImgExtension = "jpg"
const val testHeroImage = "$testHeroImgUrl$INCREDIBLE_LANDSCAPE$testHeroImgExtension"
const val testPageOffset = 5
const val testPageOffsetInitial = 0
const val testPageSizeLimit = 5

val testHero = Hero(id = testHeroId, name = testHeroName, imageUrl = testHeroImage)
val testHeroList = listOf(testHero)

val testLocalHero = LocalHero.fromModel(testHero)
val testLocalHeroWithOffset = LocalHero.fromModel(testHero, testPageOffset)
val testLocalHeroList = listOf(testLocalHero)

val testHeroesPage = HeroesPage(
    pageSize = testHeroList.size,
    pageOffset = testPageOffset,
    heroesList = testHeroList
)

val testHeroesPageEmpty = HeroesPage(
    pageSize = 0,
    pageOffset = testPageOffset,
    heroesList = emptyList()
)

val testRemoteHero = RemoteHero(
    id = testHeroId,
    name = testHeroName,
    thumbnail = RemoteImageResource(path = testHeroImgUrl, extension = testHeroImgExtension)
)

val testRemoteHeroPage = RemoteHeroPage(
    offset = testPageOffset,
    limit = testHeroList.size,
    results = listOf(testRemoteHero)
)

val testRemoteHeroResponse = RemoteHeroResponse(
    data = testRemoteHeroPage
)

const val testTimeStamp = "1234567890"
const val testHash = "abcdef1234567890"
