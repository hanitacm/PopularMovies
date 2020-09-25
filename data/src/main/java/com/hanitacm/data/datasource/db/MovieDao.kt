package com.hanitacm.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAll(): Maybe<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>): Single<List<Long>>
}