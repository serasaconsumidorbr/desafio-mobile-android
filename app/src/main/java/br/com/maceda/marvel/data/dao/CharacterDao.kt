package br.com.maceda.marvel.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.maceda.marvel.data.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:characterIds)")
    suspend fun findByIds(characterIds: IntArray): List<Character>

    @Query("SELECT * FROM character WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): Character

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg characters: Character)

    @Delete
    suspend fun delete(user: Character)

    // parametro order by não funciona recebendo como parametro
    //@Query("SELECT * FROM character ORDER BY name LIMIT :limit OFFSET :offset ")
    //suspend fun getPagination(offset: Int, limit: Int): List<CharacterModel>
    @RawQuery
    suspend fun getPaginationByRawQuery(query: SupportSQLiteQuery): List<Character>

    suspend fun list(offset: Int, limit: Int, orderBy: String = "name"): List<Character>{
        val sql = "SELECT * FROM character ORDER BY $orderBy LIMIT $limit OFFSET $offset"
        val query = SimpleSQLiteQuery(sql)
        return getPaginationByRawQuery(query)
    }


}