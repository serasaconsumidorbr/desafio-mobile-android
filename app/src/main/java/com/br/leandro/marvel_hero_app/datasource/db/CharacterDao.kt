package com.br.leandro.marvel_hero_app.datasource.db

import androidx.room.*
import com.br.leandro.marvel_hero_app.datasource.db.model.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Query("Select * From Character Order By name Asc")
    fun getMySquad(): Flowable<MutableList<CharacterEntity>>

    @Query("SELECT * FROM Character Where id = :characterId")
    fun getCharacterById(characterId: Int): Single<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun hireCharacter(characterEntity: CharacterEntity): Completable

    @Delete
    fun fireCharacter(characterEntity: CharacterEntity): Completable
}