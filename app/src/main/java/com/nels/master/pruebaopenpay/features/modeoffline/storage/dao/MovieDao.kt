package com.nels.master.pruebaopenpay.features.modeoffline.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity

@Dao
interface MovieDao {
/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(obj: I)

    @Transaction
    fun createAll(objects: List<I>) = objects.forEach {insert(it)}

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createAll(objects: List<MovieDb>)
*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMovies(objects: List<MovieEntity>)

    @Query("SELECT * from MovieEntity")
    fun getMovies(): List<MovieEntity>

    @Query("SELECT * from MovieEntity WHERE typeSource = :typeMovieCategory ")
    fun getFilterMovies(typeMovieCategory: Int): List<MovieEntity>

}

