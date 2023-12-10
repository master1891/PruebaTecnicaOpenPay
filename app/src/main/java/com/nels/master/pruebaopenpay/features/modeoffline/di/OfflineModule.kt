package com.nels.master.pruebaopenpay.features.modeoffline.di

import android.content.Context
import androidx.room.Room

import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.MoviesDatabase
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetyAllMoviesDbImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OfflineModule {

    /*
    @Singleton
    @Provides
    fun getDatabaseMovies(@ApplicationContext context: Context):MoviesDatabase{
        return Room.databaseBuilder(context,MoviesDatabase::class.java,"movie_db").build()
    }

     */

    //Tablas
    @Singleton
    @Provides
    fun getAllMoviesImpl(impl: GetyAllMoviesDbImpl):MoviesDbRepository{
        return impl
    }

    @Singleton
    @Provides
    fun provieMovieDao(@ApplicationContext context: Context):MovieDao{
       val moviesDatabase = Room.databaseBuilder(context,MoviesDatabase::class.java,"movie_db").build()
        return moviesDatabase.movieDao
    }

}