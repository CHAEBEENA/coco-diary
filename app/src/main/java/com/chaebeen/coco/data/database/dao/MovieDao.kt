package com.chaebeen.coco.data.database.dao

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.room.*
import com.chaebeen.coco.data.database.model.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<MovieEntity>>

    @Insert
    suspend fun insert(movie: MovieEntity)

    @Update
    suspend fun update(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movie")
    suspend fun delete()

    @Query("SELECT * FROM movie WHERE id=:movieId")
    suspend fun select(movieId: Int): MovieEntity
}
