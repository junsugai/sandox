package com.campus.disneycompose.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.campus.disneycompose.model.Poster

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosterList(posters: List<Poster>)

    @Query("SELECT * FROM Poster WHERE id = :id_")
    fun getPoster(id_: Long): LiveData<Poster>

    @Query("SELECT * FROM Poster")
    suspend fun getPosterList(): List<Poster>
}
