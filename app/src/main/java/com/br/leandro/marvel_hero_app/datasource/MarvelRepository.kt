package com.br.leandro.marvel_hero_app.datasource

import com.br.leandro.marvel_hero_app.datasource.db.AppDatabase
import com.br.leandro.marvel_hero_app.datasource.db.model.CharacterEntity
import com.br.leandro.marvel_hero_app.datasource.network.MarvelApi
import com.br.leandro.marvel_hero_app.datasource.network.model.Characters
import com.br.leandro.marvel_hero_app.datasource.network.model.Comics
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IMarvelRepository {
    fun getCharactersApi(offset: Int = 0): Flowable<Characters>
    fun getCharacterByIdApi(characterId: Int): Single<Characters>
    fun getComicsByCharacterIdApi(characterId: Int): Flowable<Comics>

    fun getCharacterByIdDatabase(characterId: Int): Single<CharacterEntity>
    fun hireCharacter(characterEntity: CharacterEntity): Completable
    fun fireCharacter(characterEntity: CharacterEntity): Completable
    fun getMySquad(): Flowable<MutableList<CharacterEntity>>
}

class MarvelRepository(private val db: AppDatabase, private val api: MarvelApi) :
    IMarvelRepository {

    override fun getCharactersApi(offset: Int) = api.getCharacters(offset)
    override fun getCharacterByIdApi(characterId: Int) = api.getCharacterById(characterId)
    override fun getComicsByCharacterIdApi(characterId: Int) = api.getComicsByCharacterId(characterId)

    override fun getCharacterByIdDatabase(characterId: Int) = db.characterDao.getCharacterById(characterId)
    override fun hireCharacter(characterEntity: CharacterEntity) = db.characterDao.hireCharacter(characterEntity)
    override fun fireCharacter(characterEntity: CharacterEntity) = db.characterDao.fireCharacter(characterEntity)
    override fun getMySquad() = db.characterDao.getMySquad()
}