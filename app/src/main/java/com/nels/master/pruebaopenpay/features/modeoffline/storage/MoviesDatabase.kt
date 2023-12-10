package com.nels.master.pruebaopenpay.features.modeoffline.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity

@Database(entities = [MovieEntity::class],version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}