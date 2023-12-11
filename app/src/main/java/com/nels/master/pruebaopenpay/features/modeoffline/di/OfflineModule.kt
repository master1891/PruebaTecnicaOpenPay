package com.nels.master.pruebaopenpay.features.modeoffline.di

import android.content.Context
import androidx.room.Room
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbByFilterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRegisterRepository

import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileRegisterDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.MoviesDatabase
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.ProfileDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetAllMoviesDbImpl
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetMoviesDbByFilterImpl
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetProfileDbImpl
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetProfileRegisterDbImpl
import com.nels.master.pruebaopenpay.features.modeoffline.storage.impl.GetRegisterMoviesDbImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OfflineModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):MoviesDatabase{
        return Room.databaseBuilder(context,MoviesDatabase::class.java,"app_db").build()
    }

    //Tablas
    @Singleton
    @Provides
    fun provieMovieDao(moviesDatabase: MoviesDatabase):MovieDao{
        return moviesDatabase.movieDao
    }

    @Singleton
    @Provides
    fun provieProfileDao(moviesDatabase: MoviesDatabase):ProfileDao{
        return moviesDatabase.prifileDao
    }

    //Interfaces de peliculas
    @Singleton
    @Provides
    fun provideMoviewDbByFilterImpl(impl: GetMoviesDbByFilterImpl):MoviesDbByFilterRepository{
        return impl
    }

    @Singleton
    @Provides
    fun provideMoviesDbRegisterImpl(impl: GetRegisterMoviesDbImpl):MoviesDbRegisterRepository{
        return impl
    }

    @Singleton
    @Provides
    fun provideAllMoviesImpl(impl: GetAllMoviesDbImpl ):MoviesDbRepository{
        return impl
    }

    //Interfaces del perfil
    @Singleton
    @Provides
    fun provideProfileDbImpl(impl: GetProfileDbImpl ):ProfileDbRepository{
        return impl
    }

    @Singleton
    @Provides
    fun provideProfileRegisterDbImpl(impl: GetProfileRegisterDbImpl ):ProfileRegisterDbRepository{
        return impl
    }

}